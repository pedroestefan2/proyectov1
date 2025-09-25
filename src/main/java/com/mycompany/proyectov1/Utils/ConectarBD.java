/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class ConectarBD {
//     private static final String URL = "jdbc:mysql://localhost:3306/tu_basedatos";
//    private static final String USER = "root";
//    private static final String PASSWORD = "1234";

//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
    public static Connection ConectarDB() {
        try {
            File archivoBD = new File("Prueba.db");
            boolean existe = archivoBD.exists();
            
            Class.forName("org.sqlite.JDBC");
            
            String URL = "jdbc:sqlite:" + archivoBD.getAbsolutePath();
            Connection conexion = DriverManager.getConnection(URL);
            
            if (!existe) {// si no existe lo creo
                TablasBD.inicializarBD(conexion);
                System.out.println("La base de datos no existe y se a creado correctamente");
            }
            
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error al conectar con la base de datos");
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
}
