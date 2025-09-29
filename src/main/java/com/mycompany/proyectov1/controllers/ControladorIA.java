/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.dao.ComprobarBarcoBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Usuario;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pedro
 */
public class ControladorIA {

    private ArrayList<Barco> mazo;

//    creamos un metodo para cambiar barcos al inicio del combate
    public static ArrayList<Barco> CambiarBarcoMazoIA(Usuario usuario) {
        ArrayList<Barco> barcosTotales = ComprobarBarcoBD.BuscarBarcosUsuarioBD(usuario);
        ComprobarBarcoBD.QuitarBarcosMazo(usuario);

        Random num = new Random();
        Random rand = new Random();
        ArrayList<Barco> seleccion = new ArrayList<>();
        ArrayList<Barco> copia = new ArrayList<>(barcosTotales);
        for (int i = 0; i < 3; i++) {
            int idx = rand.nextInt(copia.size());
            seleccion.add(copia.remove(idx));
        }

        return null;

    }
}
