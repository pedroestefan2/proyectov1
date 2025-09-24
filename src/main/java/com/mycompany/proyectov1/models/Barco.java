/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.models;

/**
 *
 * @author pedro
 */
public class Barco {

    private int id;
    private String nombre;
    private String elemento;
    private int vida;
    private int ataque;
    private int defensa;

    public Barco(String nombre, int vida, int ataque, int defensa) {
        //id
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getElemento() {
        return elemento;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

}
