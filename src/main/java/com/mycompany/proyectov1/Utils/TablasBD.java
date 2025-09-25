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
        turno_actual INTEGER,
        fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        fecha_terminacion TIMESTAMP
    );
""";

            String tablaAcciones = """
    CREATE TABLE IF NOT EXISTS Acciones (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_batalla INTEGER,
        id_jugador INTEGER,
        id_barco INTEGER,
        id_movimiento INTEGER,
        daño INTEGER,
        estado_aplicado TEXT,
        fecha_accion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (id_batalla) REFERENCES Batallas(id),
        FOREIGN KEY (id_jugador) REFERENCES Usuario(id),
        FOREIGN KEY (id_barco) REFERENCES Barcos(id),
        FOREIGN KEY (id_movimiento) REFERENCES Movimientos(id)
    );
""";

            String tablaChats = """
    CREATE TABLE IF NOT EXISTS Chats (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_usuario1 INTEGER NOT NULL,
        id_usuario2 INTEGER NOT NULL,
        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        CONSTRAINT unique_chat UNIQUE (id_usuario1, id_usuario2),
        CHECK (id_usuario1 < id_usuario2),
        FOREIGN KEY (id_usuario1) REFERENCES Usuario(id) ON DELETE CASCADE,
        FOREIGN KEY (id_usuario2) REFERENCES Usuario(id) ON DELETE CASCADE
    );
""";

            String tablaMensajes = """
    CREATE TABLE IF NOT EXISTS Mensajes (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_chat INTEGER NOT NULL,
        id_emisor INTEGER NOT NULL,
        mensaje TEXT,
        imagen TEXT,
        fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (id_chat) REFERENCES Chats(id) ON DELETE CASCADE,
        FOREIGN KEY (id_emisor) REFERENCES Usuario(id) ON DELETE CASCADE
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
            stm.execute(tablaAcciones);
            stm.execute(tablaChats);
            stm.execute(tablaMensajes);

//            agregamos usuarios admin y maquina
            String insertUsuarios = """
                   INSERT INTO Usuario (
                   nombreusuario, nombre, apellidos, telefono, correo, contraseña, imagen, oro, hierro, suministros
                   ) VALUES
                   ('root', 'Administrador', 'Sistema', '123456789', 'root@example.com', 'root', '/images/imagenporDefecto.png', 1000, 500, 800),
                   ('Magallanes', 'AI', 'Robot', '987654321', 'maquina@example.com', 'root', '/images/imagenporDefecto.png', 800, 300, 600);
                  """;
            
            
            stm.executeUpdate(insertUsuarios);
            System.out.println("Tabla creada");
        } catch (SQLException ex) {
            System.err.println("Error a crear la tabla:" + ex.getMessage());
            Logger.getLogger(TablasBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
