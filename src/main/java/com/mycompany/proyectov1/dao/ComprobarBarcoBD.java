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
public class ComprobarBarcoBD {

    public static Barco BuscarBarcoBD() {

        return null;

    }
    
    public static ArrayList<Barco> BuscarBarcosUsuarioBD(Usuario usuario) {
        String consulta="Select Barco.* From Usuario_Barco JOIN Barcos on id_usuario=id_usuario WHERE id_usuario=?";
         ArrayList<Barco> listabarcos = new ArrayList<>();
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Barco barco = new Barco();
                    barco.setId(rs.getInt("id"));
                    barco.setNombre(rs.getString("nombre"));
                    barco.setNivel(rs.getInt("nivel"));
                    barco.setVida(rs.getInt("vida"));
                    barco.setAtaque(rs.getInt("defensa"));
                    barco.setDefensa(rs.getInt("defensa"));
                    barco.setTipo(rs.getString("tipo"));
                    barco.setImagen(rs.getString("imagen"));
                    listabarcos.add(barco);
                }
            }
            return listabarcos;
        
        
    }   catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<Barco> CambiarBarcosMazoIABD(Usuario usuario) {// la ia tiene 10 barcos y el mazo se cambia automaticamente
        
        return null;
        
    }

    public static ArrayList<Barco> BuscarBarcosMazoBD(Usuario usuario) {
        String consulta = "SELECT Barco.* FROM Usuario_Barco JOIN Barcos ON id_usuario=id_usuario WHERE id_usuario=? AND es_en_mazo=TRUE";
        ArrayList<Barco> listabarcos = new ArrayList<>();
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Barco barco = new Barco();
                    barco.setId(rs.getInt("id"));
                    barco.setNombre(rs.getString("nombre"));
                    barco.setNivel(rs.getInt("nivel"));
                    barco.setVida(rs.getInt("vida"));
                    barco.setAtaque(rs.getInt("defensa"));
                    barco.setDefensa(rs.getInt("defensa"));
                    barco.setTipo(rs.getString("tipo"));
                    barco.setImagen(rs.getString("imagen"));
                    listabarcos.add(barco);
                }
            }
            return listabarcos;

        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
        private static boolean AñadirbarcoUSuario(Usuario usuario, Barco barco) {
        String consulta = "INSERT INTO Usuario_Barco (id_usuario,id_barco,es_en_mazo) VALUES(?,?,false)";
        try(Connection conexion=ConectarBD.ConectarDB();
                PreparedStatement st= conexion.prepareStatement(consulta)){
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());
           
            try(ResultSet rs=st.executeQuery()){
                return rs.next();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }


    private static boolean AñadirMazobarco(Usuario usuario, Barco barco) {
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=TRUE WHERE id_usuario=? AND id_barco=?";
        try(Connection conexion=ConectarBD.ConectarDB();
                PreparedStatement st= conexion.prepareStatement(consulta)){
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());
           
            try(ResultSet rs=st.executeQuery()){
                return rs.next();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean QuitarMazoBarco(Usuario usuario, Barco barco) {
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=FALSE WHERE id_usuario=? AND id_barco=?";
        try(Connection conexion=ConectarBD.ConectarDB();
                PreparedStatement st= conexion.prepareStatement(consulta)){
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());
            
            try(ResultSet rs=st.executeQuery()){
                return rs.next();
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }


        return false;

    }

    public static void QuitarBarcosMazo(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    

}
