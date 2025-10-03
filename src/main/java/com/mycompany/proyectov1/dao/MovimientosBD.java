/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.dao;

import com.mycompany.proyectov1.Utils.ConectarBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Movimiento;
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
public class MovimientosBD {
    
    public static ArrayList<Movimiento> CargarMovimientos(Barco barco){
  
            
        String consulta="SELECT Movimientos.*FROM Movimientos INNER JOIN Barco_Movimiento ON Movimientos.id = Barco_Movimiento.id_movimiento WHERE Barco_Movimiento.id_barco = ?";
        ArrayList<Movimiento> lista= new ArrayList<>();
        
        try(Connection conexion=ConectarBD.ConectarDB();
                PreparedStatement st=conexion.prepareStatement(consulta)){
            st.setInt(1, barco.getId());
            
            try(ResultSet rs=st.executeQuery()){
                while(rs.next()){
                    Movimiento mov=new Movimiento();
                    mov.setId(rs.getInt("id"));
                    mov.setNombre(rs.getString("nombre"));
                    mov.setId_tipo(rs.getInt("id_tipo"));
                    mov.setPoder(rs.getInt("poder"));
                    mov.setAcierto(rs.getInt("acierto"));
                    mov.setPp(rs.getInt("pp"));
                    mov.setTipo(rs.getString("tipo"));
                    lista.add(mov);
                }
                
            }
            return lista;
            
        } catch (SQLException ex) {
            System.out.println("rr CargarMOvi");
                    
            Logger.getLogger(MovimientosBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }
    
    public static double obtenerEfectividad(int idAtacante,int idDefensor){
         String consulta = "SELECT efectividad FROM Efectividad_Movimiento WHERE id_tipo_atacante = ? AND id_tipo_defensor = ?";

        try (Connection conexion = ConectarBD.ConectarDB(); 
                PreparedStatement st = conexion.prepareStatement(consulta)) {
            st.setInt(1, idAtacante);
            st.setInt(2, idDefensor);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    
                    return rs.getDouble("efectividad");

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
    
    public static int obtenerIdTipoNombre(String nombreTipo) {
    String consulta = "SELECT id FROM Tipos_Movimiento WHERE nombre = ?";
    
    try (Connection conexion = ConectarBD.ConectarDB();
         PreparedStatement st = conexion.prepareStatement(consulta)) {
        
        st.setString(1, nombreTipo);
        
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(ComprobarUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1; //  valor que indique que no se encontró
}

    
}
