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
        String consulta = "Select Barcos.* From Usuario_Barco JOIN Barcos on id_usuario=id_usuario WHERE id_usuario=?";
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
            return null;
        }
    }

    public static ArrayList<Barco> BuscarBarcosUsuarioIdBD(int usuarioID) {
        String consulta = "Select Barcos.* From Usuario_Barco JOIN Barcos on id_usuario=id_usuario WHERE id_usuario=?";
        ArrayList<Barco> listabarcos = new ArrayList<>();
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuarioID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
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
            return null;
        }
    }

    public static boolean CambiarBarcosMazoIABD(Usuario usuario, ArrayList<Barco> seleccionFianal) {// sellecion son 3 barcos
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=TRUE WHERE id_usuario=? AND id_barco IN (?,?,?)";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());
            st.setInt(2, seleccionFianal.get(1).getId());
            st.setInt(3, seleccionFianal.get(2).getId());
            st.setInt(4, seleccionFianal.get(3).getId());

            int filactualizadas = st.executeUpdate();
            return filactualizadas == 3;
        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static ArrayList<Barco> BuscarBarcosMazoBD(Usuario usuario) {
        String consulta = "SELECT Barcos.* FROM Usuario_Barco JOIN Barcos ON id_usuario=id_usuario WHERE id_usuario=? AND es_en_mazo=TRUE";
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

    public static ArrayList<Barco> BuscarBarcosMazoIdBD(int usuarioID) {
        String consulta = "SELECT Barcos.* FROM Usuario_Barco JOIN Barcos ON Usuario_Barco.id_barco=Barcos.id WHERE Usuario_Barco.id_usuario=? AND Usuario_Barco.es_en_mazo=TRUE";
        ArrayList<Barco> listabarcos = new ArrayList<>();
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuarioID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
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
            return null;
        }

    }

    private static boolean AñadirbarcoUSuario(Usuario usuario, Barco barco) {
        String consulta = "INSERT INTO Usuario_Barco (id_usuario,id_barco,es_en_mazo) VALUES(?,?,false)";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    private static boolean AñadirMazobarco(Usuario usuario, Barco barco) {//cambiar
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=TRUE WHERE id_usuario=? AND id_barco=?";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean QuitarMazoBarco(Usuario usuario, Barco barco) {//cambiar
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=FALSE WHERE id_usuario=? AND id_barco=?";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());
            st.setInt(2, barco.getId());

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean QuitarBarcosMazo(Usuario usuario) {
        String consulta = "UPDATE Usuario_Barco SET es_en_mazo=FALSE WHERE id_usuario=?";
        try (Connection conexion = ConectarBD.ConectarDB(); PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, usuario.getId());

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprobarBarcoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

}
