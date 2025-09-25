/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.dao;

import com.mycompany.proyectov1.Utils.ConectarBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class RegistrarUsuarioBD {

    public static boolean registrarUsuario( String nombreUsuario, String nombre,String apellidos,String telefono,String correo,String contraseña) {

         String sql = "INSERT INTO Usuario (nombreusuario, nombre, apellidos, telefono, correo, contraseña, imagen, oro, hierro, suministros) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConectarBD.ConectarDB();
             PreparedStatement st = conexion.prepareStatement(sql)) {

            st.setString(1, nombreUsuario);
            st.setString(2, nombre);
            st.setString(3, apellidos);
            st.setString(4, telefono);
            st.setString(5, correo);
            st.setString(6, contraseña);
            st.setString(7, "/images/imagenporDefecto.png"); 
            st.setInt(8, 600);
            st.setInt(9, 0);
            st.setInt(10, 0);

            int filasInsertadas = st.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(RegistrarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
