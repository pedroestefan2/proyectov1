/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.models;

/**
 *
 * @author pedro
 */
public class Movimiento {

    private int id;
    private String nombre;
    private int id_tipo;
    private int poder;
    private int acierto;
    private int pp;
    private String tipo;

    public Movimiento(int id, String nombre, int id_tipo, int acierto, int pp, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.id_tipo = id_tipo;
        this.acierto = acierto;
        this.pp = pp;
        this.tipo = tipo;
    }

    public Movimiento() {
        
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public int getAcierto() {
        return acierto;
    }

    public int getPp() {
        return pp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }
    
    public void setAcierto(int acierto) {
        this.acierto = acierto;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
