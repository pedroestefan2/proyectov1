/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.interfaces;

import com.mycompany.proyectov1.models.Usuario;

/**
 *
 * @author pedro
 */
public interface ControladorConUsuario {
//    interfaz para poder pasar usuarios sin que peta abrir ventanas por que esto se buguea y da error:
//    FXML_PrincipalJugarController controlador = loader.getController();
//    controlador.setUsuario(usuario);  aunque los tengas en diferentes metodos al llamar un controlador distinto peta
    
    void setUsuario(Usuario usuario);
    
    
    
}
