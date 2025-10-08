/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.models.Movimiento;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 *
 * @author pedro
 */
public class BatallaBarcos {

    private ControladorUsuario jugador1;
    private ControladorUsuario jugador2;
    private int turno;
   

    public BatallaBarcos(ControladorUsuario j1, ControladorUsuario j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.turno = 1;
        iniciarTurno();
    }

    private void iniciarTurno() {
        System.out.println("Turno " + turno + " iniciado.");

        // Si hay un temporizador activo, lo cancelamos
        
    }

    public void jugadorRealizaAccion() {
        System.out.println("Jugador " + turno + " realizó su acción.");
        // Detenemos el temporizador porque el jugador ya actuó
        
        pasarTurno();
    }

    public void pasarTurno() {
        if (turno == 1) {
            turno = 2;
        } else {
            turno = 1;
        }
        iniciarTurno();
    }

    public int getTurno() {
        return turno;
    }

    //combate
}
