/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.dao;

import com.mycompany.proyectov1.Utils.ConectarBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class ComprobarUsuarioBD {

    public static boolean existeUsuario(String nombreUsuario) {
        String consulta = "SELECT * FROM Usuario WHERE nombreusuario = ?";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, nombreUsuario);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean existeCorreo(String correo) {
        String consulta = "SELECT * FROM Usuario WHERE correo = ?";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, correo);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // recibe el username y la contraseña para comprobarlo es un login 
    public static Usuario login(String nombreUsuario, String contraseña) {
        String consulta = "SELECT * FROM Usuario WHERE nombreusuario = ? AND contraseña = ?";

        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, nombreUsuario);
            st.setString(2, contraseña);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombreUsuario(rs.getString("nombreusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setImagen(rs.getString("imagen"));
                    usuario.setOro(rs.getInt("oro"));
                    usuario.setHierro(rs.getInt("hierro"));
                    usuario.setSuministros(rs.getInt("suministros"));
                    return usuario;

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Usuario BuscarUsuarioNombre(String nombreUsuario) {
        String consulta = "SELECT * FROM Usuario WHERE nombreusuario = ?";

        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, nombreUsuario);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombreUsuario(rs.getString("nombreusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setImagen(rs.getString("imagen"));
                    usuario.setOro(rs.getInt("oro"));
                    usuario.setHierro(rs.getInt("hierro"));
                    usuario.setSuministros(rs.getInt("suministros"));
                    return usuario;

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    
    public static Usuario BuscarUsuarioMazoNombre(String nombreUsuario) {
        String consulta = "SELECT * FROM Usuario WHERE nombreusuario = ?";

        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, nombreUsuario);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String contraseña = rs.getString("contraseña");
                String imagen = rs.getString("imagen");
                int oro = rs.getInt("oro");
                int hierro = rs.getInt("hierro");
                int suministros = rs.getInt("suministros");

                
                
                ArrayList<Barco> barcosEnMazo = ComprobarBarcoBD.BuscarBarcosMazoIdBD(id);
                Usuario usuario = new Usuario(id, nombreUsuario, nombre, apellidos, telefono, correo, contraseña, imagen, oro, hierro, suministros);
         
                
                usuario.setListabarcosMazo(barcosEnMazo);
                return usuario;

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


    public static Usuario BuscarUsuarioCompletoNombre(String nombreUsuario) {
        String consulta = "SELECT * FROM Usuario WHERE nombreusuario = ?";

        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setString(1, nombreUsuario);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String contraseña = rs.getString("contraseña");
                String imagen = rs.getString("imagen");
                int oro = rs.getInt("oro");
                int hierro = rs.getInt("hierro");
                int suministros = rs.getInt("suministros");

                
                ArrayList<Barco> barcosTotales = ComprobarBarcoBD.BuscarBarcosUsuarioIdBD(id);
                ArrayList<Barco> barcosEnMazo = ComprobarBarcoBD.BuscarBarcosMazoIdBD(id);
                Usuario usuario = new Usuario(id, nombreUsuario, nombre, apellidos, telefono, correo, contraseña, imagen, oro, hierro, suministros);
         
                usuario.setListabarcos(barcosTotales);
                usuario.setListabarcosMazo(barcosEnMazo);
                return usuario;

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
