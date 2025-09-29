/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.Utils;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class TablasBD {

    public static void inicializarBD(Connection conexion) {
        try (Statement stm = conexion.createStatement()) {//Aqui creo las tablas

            String tablaUsuario = """
                     CREATE TABLE IF NOT EXISTS Usuario (
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     nombreusuario TEXT UNIQUE NOT NULL,
                     nombre TEXT NOT NULL,
                     apellidos TEXT,
                     telefono TEXT NOT NULL,
                     correo TEXT UNIQUE,
                     contraseña TEXT NOT NULL,
                     imagen TEXT,
                     oro INTEGER,
                     hierro INTEGER, 
                     suministros INTEGER            
                      );
                    """;

            String tablaBarcos = """
                   CREATE TABLE IF NOT EXISTS Barcos (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   nombre TEXT,
                   nivel INTEGER DEFAULT 1,
                   vida INTEGER,
                   ataque INTEGER,
                   defensa INTEGER,
                   tipo TEXT,
                   imagen TEXT
                 );
                  """;

            String tablaUsuarioBarco = """
                   CREATE TABLE IF NOT EXISTS Usuario_Barco (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   id_usuario INTEGER NOT NULL,
                   id_barco INTEGER NOT NULL,
                   nivel INTEGER DEFAULT 1,
                   es_en_mazo BOOLEAN DEFAULT FALSE,
                   FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
                   FOREIGN KEY (id_barco) REFERENCES Barcos(id) ON DELETE CASCADE,
                   UNIQUE (id_usuario, id_barco)
                   );
                  """;

            String tablaTiposMovimiento = """
                   CREATE TABLE IF NOT EXISTS Tipos_Movimiento (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   nombre TEXT UNIQUE,
                   descripcion TEXT
                   );
                """;

            String tablaMovimientos = """
                   CREATE TABLE IF NOT EXISTS Movimientos (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   nombre TEXT NOT NULL,
                   id_tipo INTEGER,
                   poder INTEGER,
                   acierto INTEGER CHECK (acierto BETWEEN 0 AND 100),
                   pp INTEGER,
                   tipo TEXT,                   
                   FOREIGN KEY (id_tipo) REFERENCES Tipos_Movimiento(id)
                   );
                 """;

            String tablaEfectividadMovimiento = """
                   CREATE TABLE IF NOT EXISTS Efectividad_Movimiento (
                   id_tipo_atacante INTEGER,
                   id_tipo_defensor INTEGER,
                   efectividad DECIMAL(3, 2),
                   PRIMARY KEY (id_tipo_atacante, id_tipo_defensor),
                   FOREIGN KEY (id_tipo_atacante) REFERENCES Tipos_Movimiento(id),
                   FOREIGN KEY (id_tipo_defensor) REFERENCES Tipos_Movimiento(id)
                    );
                 """;

            String tablaBarcoMovimiento = """
                   CREATE TABLE IF NOT EXISTS Barco_Movimiento (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   id_barco INTEGER,
                   id_movimiento INTEGER,
                    FOREIGN KEY (id_barco) REFERENCES Barcos(id),
                   FOREIGN KEY (id_movimiento) REFERENCES Movimientos(id)
                     );
                """;

            String tablaBatallas = """
                   CREATE TABLE IF NOT EXISTS Batallas (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   id_jugador1 INTEGER NOT NULL,
                   id_jugador2 INTEGER NOT NULL,
                   estado TEXT DEFAULT 'en curso',
                   turno_totales INTEGER,
                   turno_jugador1 INTEGER,
                   turno_jugador2 INTEGER,                 
                   fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                   fecha_terminacion TIMESTAMP,
                   gandor TEXT,
                   perdedor TEXT                 
                  );
               """;

            

            stm.execute(tablaUsuario);
            stm.execute(tablaBarcos);
            stm.execute(tablaUsuarioBarco);
            stm.execute(tablaTiposMovimiento);
            stm.execute(tablaMovimientos);
            stm.execute(tablaEfectividadMovimiento);
            stm.execute(tablaBarcoMovimiento);
            stm.execute(tablaBatallas);
           

//            agregamos usuarios admin y maquina
            String insertUsuarios = """
                   INSERT INTO Usuario (
                   nombreusuario, nombre, apellidos, telefono, correo, contraseña, imagen, oro, hierro, suministros
                   ) VALUES
                   ('root', 'Administrador', 'Sistema', '123456789', 'root@gmail.com', 'root', '/images/imagenporDefecto.png', 1000, 500, 800),
                   ('Magallanes', 'AI', 'Robot', '987654321', 'maquina@gmail.com', 'root', '/images/imagenporDefecto.png', 800, 300, 600);
                  """;
            String insertarTipos_movimiento = """
                    INSERT INTO Tipos_Movimiento (id, nombre, descripcion) VALUES
                    (1, 'Artillería', 'Disparos potentes de cañones navales, típicos de acorazados y cruceros pesados'),
                    (2, 'Torpedo', 'Armas submarinas de alta penetración, usadas especialmente por submarinos'),
                    (3, 'Bombardeo', 'Ataques aéreos lanzados desde portaaviones o unidades especializadas'),
                    (4, 'Antisubmarino', 'Movimientos diseñados para detectar y eliminar submarinos enemigos'),
                    (5, 'Misiles', 'Tecnología avanzada de largo alcance, guiada y de alto impacto'),
                    (6, 'Soporte', 'Habilidades defensivas, de curación, refuerzo o manipulación de combate');                         
                                             """;
            String insertEfectividad_movimiento = """
                   INSERT INTO Efectividad_Movimiento (id_tipo_atacante, id_tipo_defensor, efectividad) VALUES
                    -- Artillería
                   (1, 1, 1.0),
                   (1, 2, 1.0),
                   (1, 3, 1.2),
                   (1, 4, 1.0),
                   (1, 5, 1.0),
                   (1, 6, 1.0),
                    -- Torpedo
                   (2, 1, 1.0),
                   (2, 2, 1.0),
                   (2, 3, 1.0),
                   (2, 4, 1.0),
                   (2, 5, 1.0),
                   (2, 6, 1.0),
                    -- Bombardeo
                   (3, 1, 1.0),
                   (3, 2, 1.2),
                   (3, 3, 1.0),
                   (3, 4, 0.8),
                   (3, 5, 1.3),
                   (3, 6, 1.5),
                   -- Antisubmarino
                   (4, 1, 0.8),
                   (4, 2, 1.0),
                   (4, 3, 0.8),
                   (4, 4, 2.0),  -- Muy efectivo contra submarinos
                   (4, 5, 1.0),
                   (4, 6, 1.0),
                    -- Misiles
                   (5, 1, 1.5),
                   (5, 2, 1.0),
                   (5, 3, 1.3),
                   (5, 4, 1.0),
                   (5, 5, 1.0),
                   (5, 6, 1.0),
                    -- Soporte
                   (6, 1, 0.5),
                   (6, 2, 0.5),
                   (6, 3, 0.5),
                   (6, 4, 0.5),
                   (6, 5, 0.5),
                   (6, 6, 1.0); 
                    """;
            String insertarMovimientos = """
                   INSERT INTO Movimientos (nombre, id_tipo, poder, acierto, pp ,tipo) VALUES                            
                   ('Cañón ligero', 1, 90, 95, 25,'normal'),
                   ('Explosión precisa', 1, 130, 90, 10,'normal'),
                   ('Salva torpedos', 2, 160, 80, 8,'normal'),
                   ('Torpedo guiado', 2, 140, 90, 6,'normal'),
                   ('Bombardeo rápido', 3, 120, 85, 10,'normal'),
                   ('Lluvia de bombas', 3, 150, 75, 6,'normal'),
                   ('Barrido antisubmarino', 4, 170, 85, 5,'normal'),
                   ('Caza de profundidad', 4, 150, 90, 6,'normal'),
                   ('Misil táctico', 5, 160, 85, 8,'normal'),
                   ('Carga blindada', 5, 180, 80, 5,'normal'),                --10
                   ('Escudo naval', 6, 0, 100, 10,'normal'), -- Aumenta defensa
                   ('Disparo de cobertura', 1, 70, 100, 25,'normal'),
                   ('Ataque silencioso', 2, 90, 100, 20,'normal'),
                   ('Torpedo acústico', 2, 120, 85, 10,'normal'),
                   ('Bombardeo en picado', 3, 130, 80, 8,'normal'),
                   ('Mina naval', 3, 110, 90, 10,'normal'), 
                   ('Misil de crucero', 5, 150, 90, 6,'normal'),   
                   ('Reforzar casco', 6, 0, 100, 10,'normal'), -- Aumenta defensa
                   ('Bombardeo de saturación', 3, 110, 85, 10,'normal'), 
                   ('Reparación ligera', 6, 0, 100, 15,'normal'),         -- Recupera 25% de vida
                   ('Reparación media', 6, 0, 100, 10,'normal'),          -- Recupera 50% de vida --21
                   - Movimientos especiales
                   ('Furia del Yamato', 1, 220, 90, 4,'especial'),         -- solo Yamato
                   ('Misil Tomahawk', 5, 200, 85, 3,'especial'),          -- solo EE.UU.
                   ('Cañones triples', 1, 180, 85, 5,'especial'),         -- solo Bismarck/Tirpitz
                   ('Golpe de sigilo', 2, 170, 100, 3,'especial'),        -- solo submarinos
                   ('Fuego británico', 1, 190, 90, 5,'especial'),         -- solo barcos ingleses
                   ('Bombardeo real', 3, 210, 70, 3,'especial'),          -- portaaviones reales
                   ('Explosión de cadena', 1, 160, 85, 6,'especial'),           -- para barcos pesados  
                   
                   ('Lluvia infernal', 3, 200, 80, 3,'especial'),              -- portaaviones japoneses
                   ('Emboscada táctica', 2, 140, 100, 5,'especial'),           -- submarinos alemanes     --30
                   ('Muro defensivo', 6, 0, 100, 8,'especial'),                -- reduce daño por 2 turnos
                   ('Misil de pulso', 5, 180, 85, 4,'especial'),               -- tecnología avanzada 
                   ('Fuego cruzado', 1, 150, 85, 6,'especial'),                   -- barcos con múltiples torretas
                   ('Doble torpedo', 2, 180, 75, 5,'especial'),                   -- submarinos de ataque
                   ('Fuego napalm', 3, 160, 80, 5,'especial'),                    -- portaaviones especializados
                   ('Carga electromagnética', 5, 190, 90, 4,'especial'),          -- tecnología avanzada
                   ('Campo de interferencia', 6, 0, 100, 6,'especial'),           -- baja precisión enemiga
                   ('Escudo reflectante', 6, 0, 100, 3,'especial'),               -- devuelve parte del daño 
                   ('Refuerzo del casco', 6, 0, 100, 8,'especial'),         -- Aumenta defensa durante 3 turnos
                   ('Blindaje reactivo', 6, 0, 100, 5,'especial'),          -- Reduce el daño recibido en un 50% por 2 turnos    --40
                   ('Campo de reparación', 6, 0, 100, 5,'especial'),        -- Regenera un poco de vida cada turno (por 3 turnos)
                   ('Barrera energética', 6, 0, 100, 3,'especial'),         -- Inmune al siguiente ataque            --42            
                                       
                    --Ultimates (muy poderosos, de un solo uso o muy limitado)
                    ('Tormenta de acero', 1, 300, 90, 1,'ulti'),       -- Ultimate de acorazados
                    ('Juicio naval', 5, 280, 85, 1,'ulti'),            -- Ultimate misiles
                    ('Fuerza del almirante', 6, 0, 100, 2,'ulti'),     -- Buff completo (ataque y defensa +) 
                    ('Dominio oceánico', 1, 320, 85, 1,'ulti'),             -- ultimate de control marítimo
                    ('Invasión total', 3, 300, 80, 1,'ulti'),               -- ataque aéreo masivo
                    ('Sombra abisal', 4, 290, 90, 1,'ulti'),                -- ultimate submarino
                    ('Juicio orbital', 5, 350, 75, 1,'ulti'),               -- ultimate tecnológico
                    ('Fortaleza impenetrable', 6, 0, 100, 1,'ulti'),        -- aumenta mucho la defensa   --50
                    ('Tormenta oceánica', 3, 280, 85, 1,'ulti'),               -- gran ataque de bombardeo
                    ('Tifón silencioso', 4, 260, 100, 1,'ulti'),               -- submarino especial
                    ('Impacto orbital', 5, 350, 70, 1,'ulti'),                 -- ultra tecnología
                    ('Última esperanza', 1, 300, 95, 1,'ulti'),                -- solo si la vida es baja
                    ('Orden del almirante', 6, 0, 100, 1,'ulti'),              -- aumenta todas las estadísticas   
                    ('Milagro del astillero', 6, 0, 100, 1,'ulti'),            -- Una maniobra de soporte definitiva que activa todos los sistemas de reparación, recuperando completamente la vida del barco, restaurando todos los PP y eliminando cualquier estado negativo. Además, aumenta la defensa por 3 turnos.       
                    ('Torpedo nuclear',2,250,70,1,'ulti');                   --  u 234        58                   
                                       
                                       """;

//            insertamos los barcos *añdir barcos especiales futuro
            String insertarBarcos = """
                    INSERT INTO Barcos(nombre, nivel, vida, ataque, defensa, tipo, imagen) VALUES 
                    ('Bismarck',1,2000,450,520,'acorazado','/images/imagenporDefecto.png'),  
                    ('Tirpitz',1,1900,380,600,'acorazado','/images/imagenporDefecto.png'), 
                    ('Gneisenau', 1, 1300, 360, 300, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Scharnhorst', 1, 1300, 360, 320, 'acorazado', '/images/imagenporDefecto.png'),
                    ('U-47', 1, 520, 220, 140, 'submarino', '/images/imagenporDefecto.png'),
                    ('U-234', 1, 520, 260, 200, 'submarino', '/images/imagenporDefecto.png'),
                    ('U-530', 1, 530, 220, 140, 'submarino', '/images/imagenporDefecto.png'),               
                    ('Graf Zeppelin', 1, 1170, 260, 230, 'portaaviones', '/images/imagenporDefecto.png'),--8
                    
                                   
                    ('Yamato', 1, 1950, 470, 520, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Musashi', 1, 1850, 440, 370, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Akagi', 1, 1150, 260, 220, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('Shinano', 1, 1230, 270, 240, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('Kaga', 1, 1230, 275, 240, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('Zuikaku', 1, 1220, 270, 235, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('I-400', 1, 560, 220, 170, 'submarino', '/images/imagenporDefecto.png'),
                    ('Mogami', 1, 1100, 310, 260, 'crucero', '/images/imagenporDefecto.png'),   --16            
                                   
                    ('USS Iowa', 1, 1430, 410, 350, 'acorazado', '/images/imagenporDefecto.png'),
                    ('USS Enterprise', 1, 1150, 260, 220, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('USS Arizona', 1, 1300, 320, 300, 'acorazado', '/images/imagenporDefecto.png'),
                    ('USS Missouri', 1, 1500, 430, 340, 'acorazado', '/images/imagenporDefecto.png'),
                    ('USS Nautilus', 1, 550, 190, 140, 'submarino', '/images/imagenporDefecto.png'),
                    ('USS New Jersey', 1, 1500, 410, 350, 'acorazado', '/images/imagenporDefecto.png'),
                    ('USS Wisconsin', 1, 1480, 400, 345, 'acorazado', '/images/imagenporDefecto.png'),
                    ('USS Lexington', 1, 1230, 275, 240, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('USS Hornet', 1, 1220, 265, 230, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('USS Gato', 1, 585, 230, 170, 'submarino', '/images/imagenporDefecto.png'),
                    ('USS Atlanta', 1, 1100, 310, 260, 'crucero', '/images/imagenporDefecto.png'),   --27            
                                   
                    ('HMS Hood', 1, 1300, 360, 280, 'crucero_batalla', '/images/imagenporDefecto.png'),
                    ('HMS King George V', 1, 1050, 390, 340, 'acorazado', '/images/imagenporDefecto.png'),
                    ('HMS Ark Royal', 1, 1090, 230, 210, 'portaaviones', '/images/imagenporDefecto.png'),
                    ('HMS Prince of Wales', 1, 1430, 420, 340, 'acorazado', '/images/imagenporDefecto.png'),
                    ('HMS Rodney', 1, 1450, 410, 350, 'acorazado', '/images/imagenporDefecto.png'),
                    ('HMS Warspite', 1, 1400, 370, 320, 'acorazado', '/images/imagenporDefecto.png'),
                    ('HMS Belfast', 1, 1230, 320, 260, 'crucero', '/images/imagenporDefecto.png'),
                    ('HMS Illustrious', 1, 1080, 240, 210, 'portaaviones', '/images/imagenporDefecto.png')--35               
                                   
                    ('Roma', 1, 1380, 370, 320, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Vittorio Veneto', 1, 1330, 380, 310, 'acorazado', '/images/imagenporDefecto.png'),
                                   
                    ('Richelieu', 1, 1370, 350, 320, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Jean Bart', 1, 1330, 340, 310, 'acorazado', '/images/imagenporDefecto.png'),--39
                                   
                    ('España', 1, 1170, 250, 260, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Baleares', 1, 960, 280, 220, 'crucero', '/images/imagenporDefecto.png'),
                    ('Canarias', 1, 1100, 290, 240, 'crucero', '/images/imagenporDefecto.png'),
                    ('Ciscar', 1, 700, 240, 190, 'destructor', '/images/imagenporDefecto.png'),
                    ('General Mola', 1, 930, 270, 230, 'crucero', '/images/imagenporDefecto.png'),    --44           
                     
                    ('S-13', 1, 520, 220, 160, 'submarino', '/images/imagenporDefecto.png'),               
                    ('Marat', 1, 1230, 310, 260, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Sovetsky Soyuz', 1, 1800, 440, 390, 'acorazado', '/images/imagenporDefecto.png'),
                    ('Gnevny', 1, 780, 260, 180, 'destructor', '/images/imagenporDefecto.png'),
                    ('Kirov', 1, 1100, 310, 240, 'crucero', '/images/imagenporDefecto.png'),
                    ('Leningrad', 1, 780, 260, 190, 'destructor', '/images/imagenporDefecto.png');    --50
                                   
                                         """;
            String insertablaBarcoMovimiento = """
                       INSERT INTO Barco_Movimiento (id_barco, id_movimiento) VALUES
                                                -- Barco 1
                                                (1, 1), (1, 2), (1, 3),
                                                (1, 31), (1, 32),
                                                (1, 61),
                                                
                                                -- Barco 2
                                                (2, 4), (2, 5), (2, 6),
                                                (2, 33), (2, 34),
                                                (2, 62),
                                                
                                                -- Barco 3
                                                (3, 7), (3, 8), (3, 9),
                                                (3, 35), (3, 36),
                                                (3, 63),
                                                
                                                -- Barco 4
                                                (4, 10), (4, 11), (4, 12),
                                                (4, 37), (4, 38),
                                                (4, 64),
                                                
                                                -- Barco 5
                                                (5, 13), (5, 14), (5, 15),
                                                (5, 39), (5, 40),
                                                (5, 65),
                                                
                                                -- Barco 6
                                                (6, 16), (6, 17), (6, 18),
                                                (6, 41), (6, 42),
                                                (6, 66),
                                                
                                                -- Barco 7
                                                (7, 19), (7, 20), (7, 21),
                                                (7, 43), (7, 44),
                                                (7, 67),
                                                
                                                -- Barco 8
                                                (8, 22), (8, 23), (8, 24),
                                                (8, 45), (8, 46),
                                                (8, 68),
                                                
                                                -- Barco 9
                                                (9, 25), (9, 26), (9, 27),
                                                (9, 47), (9, 48),
                                                (9, 69),
                                                
                                                -- Barco 10
                                                (10, 28), (10, 29), (10, 30),
                                                (10, 49), (10, 50),
                                                (10, 70),
                                                
                                                -- Barco 11 (normal ciclo vuelve a 1)
                                                (11, 1), (11, 2), (11, 3),
                                                (11, 31), (11, 32),
                                                (11, 61),
                                                
                                                -- Barco 12
                                                (12, 4), (12, 5), (12, 6),
                                                (12, 33), (12, 34),
                                                (12, 62),
                                                
                                                -- Barco 13
                                                (13, 7), (13, 8), (13, 9),
                                                (13, 35), (13, 36),
                                                (13, 63),
                                                
                                                -- Barco 14
                                                (14, 10), (14, 11), (14, 12),
                                                (14, 37), (14, 38),
                                                (14, 64),
                                                
                                                -- Barco 15
                                                (15, 13), (15, 14), (15, 15),
                                                (15, 39), (15, 40),
                                                (15, 65),
                                                
                                                -- Barco 16
                                                (16, 16), (16, 17), (16, 18),
                                                (16, 41), (16, 42),
                                                (16, 66),
                                                
                                                -- Barco 17
                                                (17, 19), (17, 20), (17, 21),
                                                (17, 43), (17, 44),
                                                (17, 67),
                                                
                                                -- Barco 18
                                                (18, 22), (18, 23), (18, 24),
                                                (18, 45), (18, 46),
                                                (18, 68),
                                                
                                                -- Barco 19
                                                (19, 25), (19, 26), (19, 27),
                                                (19, 47), (19, 48),
                                                (19, 69),
                                                
                                                -- Barco 20
                                                (20, 28), (20, 29), (20, 30),
                                                (20, 49), (20, 50),
                                                (20, 70),
                                                
                                                -- Barco 21
                                                (21, 1), (21, 2), (21, 3),
                                                (21, 31), (21, 32),
                                                (21, 61),
                                                
                                                -- Barco 22
                                                (22, 4), (22, 5), (22, 6),
                                                (22, 33), (22, 34),
                                                (22, 62),
                                                
                                                -- Barco 23
                                                (23, 7), (23, 8), (23, 9),
                                                (23, 35), (23, 36),
                                                (23, 63),
                                                
                                                -- Barco 24
                                                (24, 10), (24, 11), (24, 12),
                                                (24, 37), (24, 38),
                                                (24, 64),
                                                
                                                -- Barco 25
                                                (25, 13), (25, 14), (25, 15),
                                                (25, 39), (25, 40),
                                                (25, 65),
                                                
                                                -- Barco 26
                                                (26, 16), (26, 17), (26, 18),
                                                (26, 41), (26, 42),
                                                (26, 66),
                                                
                                                -- Barco 27
                                                (27, 19), (27, 20), (27, 21),
                                                (27, 43), (27, 44),
                                                (27, 67),
                                                
                                                -- Barco 28
                                                (28, 22), (28, 23), (28, 24),
                                                (28, 45), (28, 46),
                                                (28, 68),
                                                
                                                -- Barco 29
                                                (29, 25), (29, 26), (29, 27),
                                                (29, 47), (29, 48),
                                                (29, 69),
                                                
                                                -- Barco 30
                                                (30, 28), (30, 29), (30, 30),
                                                (30, 49), (30, 50),
                                                (30, 70),
                                                
                                                -- Barco 31
                                                (31, 1), (31, 2), (31, 3),
                                                (31, 31), (31, 32),
                                                (31, 61),
                                                
                                                -- Barco 32
                                                (32, 4), (32, 5), (32, 6),
                                                (32, 33), (32, 34),
                                                (32, 62),
                                                
                                                -- Barco 33
                                                (33, 7), (33, 8), (33, 9),
                                                (33, 35), (33, 36),
                                                (33, 63),
                                                
                                                -- Barco 34
                                                (34, 10), (34, 11), (34, 12),
                                                (34, 37), (34, 38),
                                                (34, 64),
                                                
                                                -- Barco 35
                                                (35, 13), (35, 14), (35, 15),
                                                (35, 39), (35, 40),
                                                (35, 65),
                                                
                                                -- Barco 36
                                                (36, 16), (36, 17), (36, 18),
                                                (36, 41), (36, 42),
                                                (36, 66),
                                                
                                                -- Barco 37
                                                (37, 19), (37, 20), (37, 21),
                                                (37, 43), (37, 44),
                                                (37, 67),
                                                
                                                -- Barco 38
                                                (38, 22), (38, 23), (38, 24),
                                                (38, 45), (38, 46),
                                                (38, 68),
                                                
                                                -- Barco 39
                                                (39, 25), (39, 26), (39, 27),
                                                (39, 47), (39, 48),
                                                (39, 69),
                                                
                                                -- Barco 40
                                                (40, 28), (40, 29), (40, 30),
                                                (40, 49), (40, 50),
                                                (40, 70),
                                                
                                                -- Barco 41
                                                (41, 1), (41, 2), (41, 3),
                                                (41, 31), (41, 32),
                                                (41, 61),
                                                
                                                -- Barco 42
                                                (42, 4), (42, 5), (42, 6),
                                                (42, 33), (42, 34),
                                                (42, 62),
                                                
                                                -- Barco 43
                                                (43, 7), (43, 8), (43, 9),
                                                (43, 35), (43, 36),
                                                (43, 63);                         
                                                """;

            stm.executeUpdate(insertUsuarios);
            System.out.println("Tabla creada");
        } catch (SQLException ex) {
            System.err.println("Error a crear la tabla:" + ex.getMessage());
            Logger.getLogger(TablasBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
