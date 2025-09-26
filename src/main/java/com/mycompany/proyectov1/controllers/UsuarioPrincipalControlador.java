/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.models.Usuario;

/**
 *
 * @author pedro
 */
public class UsuarioPrincipalControlador {

    private static Usuario usuarioprincipal;

    public static void setUsuario(Usuario usuario) {
        usuarioprincipal = usuario;
    }

    public static Usuario getUsuario() {

        return usuarioprincipal;
    }

}
