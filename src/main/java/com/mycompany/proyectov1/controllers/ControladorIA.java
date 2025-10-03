/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.dao.ComprobarBarcoBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Movimiento;
import com.mycompany.proyectov1.models.Usuario;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pedro
 */
public class ControladorIA extends ControladorUsuario {

    private ArrayList<Barco> mazo;
    Random random = new Random();

    public ControladorIA(Usuario usuario) {
        super(usuario);
    }

    public boolean realizarAccion(ControladorUsuario def) {
        EstadoBarcoBatalla barco = this.getPrimerVivo();
        EstadoBarcoBatalla defensor = def.getPrimerVivo();
        // Elegir un movimiento usable aleatorio
        ArrayList<Movimiento> movimientosUsables = new ArrayList<>();
        for (Movimiento m : barco.getMovimientos()) {
            if (m != null && m.getPp() > 0 && m.isUsable()) {
                movimientosUsables.add(m);
            }
        }
        if (movimientosUsables.isEmpty()) {
            System.out.println("[IA] No tiene movimientos utilizables.");
            return false;
        }

        Movimiento movElegido = movimientosUsables.get(random.nextInt(movimientosUsables.size()));

        System.out.println("[IA] El barco " + barco.getBarco().getNombre()
                + " ataca con " + movElegido.getNombre());

        int daño = this.ataque(barco, defensor, movElegido);

        if (daño == 0) {
            System.out.println("[IA] El ataque falló.");
        } else if (daño < 0) {
            System.out.println("[IA] El barco se curó " + (-daño) + " puntos.");
        } else {
            System.out.println("[IA] Hizo " + daño + " de daño.");
        }

        return false;
    }

//    creamos un metodo para cambiar barcos al inicio del combate
    public static ArrayList<Barco> CambiarBarcoMazoIA(Usuario usuario) {
        ArrayList<Barco> barcosTotales = ComprobarBarcoBD.BuscarBarcosUsuarioIdBD(usuario.getId());
        ComprobarBarcoBD.QuitarBarcosMazo(usuario);

        Random rand = new Random();

        ArrayList<Barco> seleccionFinal = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int num = rand.nextInt(barcosTotales.size());
            Barco b = barcosTotales.remove(num);
            seleccionFinal.add(b);

        }
        ComprobarBarcoBD.CambiarBarcosMazoIABD(usuario, seleccionFinal);
        return seleccionFinal;

    }

}
