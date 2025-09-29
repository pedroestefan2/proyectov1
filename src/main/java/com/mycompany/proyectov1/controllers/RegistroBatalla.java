/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

/**
 *
 * @author pedro
 */
public class RegistroBatalla {
//    registro de batalla

    private static StringBuilder registroUsuario1 = new StringBuilder();
    private static StringBuilder registroUsuario2 = new StringBuilder();

    public static String getRegistroUsuario1() {
        return registroUsuario1.toString();
    }

    public static void setRegistroUsuario1(String registro) {
        RegistroBatalla.registroUsuario1.setLength(0);
        RegistroBatalla.registroUsuario1.append(registro);
    }

    public static String getRegistroUsuario2() {
        return registroUsuario2.toString();
    }

    public static void setRegistroUsuario2(String registro) {
        RegistroBatalla.registroUsuario2.setLength(0);
        RegistroBatalla.registroUsuario2.append(registro);
    }

}
