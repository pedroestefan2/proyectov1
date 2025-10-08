/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.controllers;

import com.mycompany.proyectov1.dao.ComprobarBarcoBD;
import com.mycompany.proyectov1.dao.MovimientosBD;
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

    @Override
    public String ataque(EstadoBarcoBatalla atacante, EstadoBarcoBatalla defensor, Movimiento mov) {

        double acierto = mov.getAcierto();
        int aleatorio = (int) (Math.random() * 100) + 1;
        if (aleatorio > acierto) {
            return "La IA ha fallado el ataque";

        }

        // 2. Si es curación
        if (mov.getPp() < 0 || mov.getTipo().equalsIgnoreCase("curacion")) {
            int curacion = Math.abs(mov.getPp());
            int vidaMax = atacante.getBarco().getVida();
            int nuevaVida = atacante.getVidaActual() + curacion;
            atacante.setVidaActual(Math.min(nuevaVida, vidaMax));
            return "El barco enemigo se curo " + curacion + " puntos de vida.";

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
        return "La IA ha acertado el ataque : " + dañoFinal + " de daño. Efectividad: " + efectividad;

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
