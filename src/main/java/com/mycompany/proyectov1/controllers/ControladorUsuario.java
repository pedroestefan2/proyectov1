/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.dao.MovimientosBD;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Movimiento;
import com.mycompany.proyectov1.models.Usuario;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class ControladorUsuario {

    private int id;
    private String nombre;
    private ArrayList<EstadoBarcoBatalla> listabarcos = new ArrayList<>();

    public ControladorUsuario(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombreUsuario();
        for (Barco b : usuario.getListabarcosMazo()) {
            listabarcos.add(new EstadoBarcoBatalla(b));// no hace falta comprobar creo
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<EstadoBarcoBatalla> getListabarcos() {
        return listabarcos;
    }

    public EstadoBarcoBatalla getPrimerVivo() {
        for (EstadoBarcoBatalla b : listabarcos) {
            if (b.getEstaVivo()) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<EstadoBarcoBatalla> getEstanvivos() {
        ArrayList<EstadoBarcoBatalla> listabarcosVivos = new ArrayList<>();
        for (EstadoBarcoBatalla b : listabarcos) {
            if (b.getEstaVivo()) {
                listabarcosVivos.add(b);
            }
        }
        return listabarcosVivos;
    }

    public void aumentarEstados() {
        for (EstadoBarcoBatalla b : listabarcos) {
            b.setEnergiaEspecial(b.getEnergiaEspecial() + 10);
            b.setEnergiaUltimate(b.getEnergiaUltimate() + 1);
            b.actualizarMovimientosTurno();
        }
    }

    //devuelve el daño  positivo ataque daño negativo curarse 
    public int ataque(EstadoBarcoBatalla atacante, EstadoBarcoBatalla defensor, Movimiento mov) {
        double acierto = mov.getAcierto();
        int aleatorio = (int) (Math.random() * 100) + 1;
        if (aleatorio > acierto) {
            System.out.println("ataque fallido");
            return 0;
        }

        // 2. Si es curación
        if (mov.getPp() < 0 || mov.getTipo().equalsIgnoreCase("curacion")) {
            int curacion = Math.abs(mov.getPp());
            int vidaMax = atacante.getBarco().getVida();
            int nuevaVida = atacante.getVidaActual() + curacion;
            atacante.setVidaActual(Math.min(nuevaVida, vidaMax));
            System.out.println("El barco se curó " + curacion + " puntos de vida.");
            return -curacion;
        }

        // 3. Obtener valores base
        int ataque = atacante.getBarco().getAtaque();
        int defensa = defensor.getBarco().getDefensa();
        int poder = mov.getPp();

        // 4. Obtener la efectividad (tipo movimiento vs tipo barco)
        //dudas
        int idTipoAtacante = mov.getId_tipo();
        int idTipoDefensor = MovimientosBD.obtenerIdTipoNombre(defensor.getBarco().getTipo());
        double efectividad = MovimientosBD.obtenerEfectividad(idTipoAtacante, idTipoDefensor);
        if (efectividad == 0) {
            efectividad = 1.0;
        }
        // 5. Calcular daño
        int dañoBase = (ataque + poder) - defensa;
        dañoBase = Math.max(0, dañoBase); // no permitir negativos

        int dañoFinal = (int) (dañoBase * efectividad);
        dañoFinal = Math.max(0, dañoFinal); // por si se vuelve a hacer negativo

        // 6. Aplicar daño al defensor
        int nuevaVidaDefensor = defensor.getVidaActual() - dañoFinal;
        defensor.setVidaActual(Math.max(0, nuevaVidaDefensor));

        // 7. Restar energía si es especial o ulti
        switch (mov.getTipo().toLowerCase()) {
            case "especial":
                atacante.setEnergiaEspecial(0);
                break;
            case "ulti":
                atacante.setEnergiaUltimate(0);
                break;
        }

        // 8. Mostrar resultado
        System.out.println("Ataque acertado: " + dañoFinal + " de daño. Efectividad: " + efectividad);

        return dañoFinal;

    }

}
