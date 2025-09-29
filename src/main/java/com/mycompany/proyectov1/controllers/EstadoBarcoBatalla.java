/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.dao.MovimientosBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Movimiento;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class EstadoBarcoBatalla {

    private Barco barco;
    private int vidaActual;
    private int energiaEspecial;
    private int energiaUltimate;
    private ArrayList<Movimiento> movimientosTurno;
    private ArrayList<Movimiento> movimientos;

    public EstadoBarcoBatalla(Barco barco) {
        this.barco = barco;
        this.vidaActual = barco.getVida();
        this.energiaEspecial = 0;
        this.energiaUltimate = 0;
        //cargo los 6 movimientos 
        this.movimientos = MovimientosBD.CargarMovimientos(barco);
        this.movimientosTurno = new ArrayList<>();

    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public boolean getEstaVivo() {
        if (this.vidaActual == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getEnergiaEspecial() {
        return energiaEspecial;
    }

    public void setEnergiaEspecial(int energiaEspecial) {
        this.energiaEspecial = energiaEspecial;
    }

    public int getEnergiaUltimate() {
        return energiaUltimate;
    }

    public void setEnergiaUltimate(int energiaUltimate) {
        this.energiaUltimate = energiaUltimate;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void actualizarMovimientosTurno() {
        this.movimientosTurno.clear();
        ArrayList<Movimiento> dis = new ArrayList<>();
        for (Movimiento m : this.movimientos) {
            String tipo = m.getTipo();
            if (tipo.equals("normal")) {
                dis.add(m);
            } else if (tipo.equals("especial") && this.energiaEspecial >= 100) {
                dis.add(m);
            } else if (tipo.equals("ulti") && this.energiaUltimate >= 100) {
                dis.add(m);
            }
        }
        int max = Math.min(4, dis.size());
        for (Movimiento m : dis) {
            this.movimientosTurno.add(m);
        }

    }
}
