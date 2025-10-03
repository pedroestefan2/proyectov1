/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectov1.controllers.fxml;

import com.mycompany.proyectov1.Utils.AbrirVentanas;
import com.mycompany.proyectov1.controllers.BatallaBarcos;
import com.mycompany.proyectov1.controllers.ControladorIA;
import com.mycompany.proyectov1.controllers.ControladorUsuario;
import com.mycompany.proyectov1.controllers.EstadoBarcoBatalla;
import com.mycompany.proyectov1.dao.ComprobarUsuarioBD;
import com.mycompany.proyectov1.interfaces.ControladorConUsuario;
import com.mycompany.proyectov1.models.Barco;
import com.mycompany.proyectov1.models.Movimiento;
import com.mycompany.proyectov1.models.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class FXML_BattleMaquinaController implements Initializable, ControladorConUsuario {

    /**
     * Initializes the controller class.
     */
    private BatallaBarcos batalla;
    private Usuario usuarioPrincipal;
    private Usuario usuarioIA;
    private ControladorUsuario controladorJugador;
    private ControladorIA controladorIA;

    @FXML
    private Label lbContador;
    @FXML
    private Label lbUsuario1;
    @FXML
    private Label lbUsuario2;
    @FXML
    private Label lbUsuario1Vida;
    @FXML
    private Label lbUsuario2Vida;
    @FXML
    private ImageView imgBarco1;
    @FXML
    private ImageView imgBarco2;
    @FXML
    private Button btnBarco1;
    @FXML
    private Button btnBarco2;
    @FXML
    private Button btnBarco3;
    @FXML
    private Label lbDatosCombate;
    @FXML
    private Button btnAtaque1;
    @FXML
    private Button btnAtaque2;
    @FXML
    private Button btnAtaque3;
    @FXML
    private Button btnAtaque4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Combate contra la maquina");

    }

    @Override
    public void setUsuario(Usuario usuario) {

        this.usuarioPrincipal = usuario;

        //ia
        this.usuarioIA = ComprobarUsuarioBD.BuscarUsuarioNombre("Magallanes");
        this.controladorJugador = new ControladorUsuario(this.usuarioPrincipal);
        this.controladorIA = new ControladorIA(this.usuarioIA);

        this.batalla = new BatallaBarcos(this.controladorJugador, this.controladorIA);
        actualizarUI();
    }

    private void actualizarUI() {
        if (controladorJugador == null || controladorIA == null) {
            return;
        }
        lbUsuario1.setText(controladorJugador.getNombre());
        lbUsuario2.setText(controladorIA.getNombre());

        //barcos jugador
        ArrayList<EstadoBarcoBatalla> barcosJugador = controladorJugador.getListabarcos();

        if (barcosJugador.size() > 0) {
            btnBarco1.setText(barcosJugador.get(0).getBarco().getNombre());
        }
        if (barcosJugador.size() > 1) {
            btnBarco2.setText(barcosJugador.get(1).getBarco().getNombre());
        }
        if (barcosJugador.size() > 2) {
            btnBarco3.setText(barcosJugador.get(2).getBarco().getNombre());
        }

        //vida de los barcos seleccionados y la imagen
        String rutaimg = controladorJugador.getBarcoSeleccionado().getBarco().getImagen();
        String rutaimg2 = controladorIA.getBarcoSeleccionado().getBarco().getImagen();
        imgBarco1.setImage(new Image(getClass().getResourceAsStream(rutaimg)));
        imgBarco2.setImage(new Image(getClass().getResourceAsStream(rutaimg2)));
        imgBarco2.setScaleX(-1);
        //asinar botones jugador al cargar la interfaz
        //actualizarBotonesAtaque(controladorJugador.getBarcoSeleccionado());
        CambiarBarcoSelecionado(new ActionEvent(btnBarco1, btnBarco1));//simula que el usuario hace click
        //vida de los barcos y cuendo se hunden
        if (controladorJugador.getBarcoSeleccionado() != null) {
            lbUsuario1Vida.setText("Vida: " + controladorJugador.getBarcoSeleccionado().getVidaActual());
        } else {
            lbUsuario1Vida.setText("Todos los barcos hundidos");
            // Lógica de fin de partida 
        }

        if (controladorIA.getBarcoSeleccionado() != null) {
            lbUsuario2Vida.setText("Vida: " + controladorIA.getBarcoSeleccionado().getVidaActual());
        } else {
            lbUsuario2Vida.setText("Todos los barcos hundidos");
            // Lógica de fin de partida 
        }

    }

    @FXML
    private void CambiarBarcoSelecionado(ActionEvent event) {

        Button boton = (Button) event.getSource();
        ArrayList<EstadoBarcoBatalla> barcosJugador = controladorJugador.getListabarcos();
        EstadoBarcoBatalla barcoSeleccionar = null;
        //Asignar botones
        if (boton == btnBarco1 && barcosJugador.size() > 0) {
            barcoSeleccionar = barcosJugador.get(0);
            controladorJugador.setBarcoSeleccionado(barcosJugador.get(0));
        } else if (boton == btnBarco2 && barcosJugador.size() > 1) {
            barcoSeleccionar = barcosJugador.get(1);
        } else if (boton == btnBarco3 && barcosJugador.size() > 2) {
            barcoSeleccionar = barcosJugador.get(2);
        }

        //Comprobar que el barco esta vivo
        if (barcoSeleccionar != null && barcoSeleccionar.getEstaVivo()) {
            controladorJugador.setBarcoSeleccionado(barcoSeleccionar);

            lbDatosCombate.setText("Has seleccionado a " + controladorJugador.getBarcoSeleccionado().getBarco().getNombre() + ". Elige un ataque.");
            //cambiar botones y imagenes
            String rutaimg = controladorJugador.getBarcoSeleccionado().getBarco().getImagen();
            imgBarco1.setImage(new Image(getClass().getResourceAsStream(rutaimg)));
            lbUsuario1Vida.setText("Vida: " + controladorJugador.getBarcoSeleccionado().getVidaActual());
            actualizarBotonesAtaque(controladorJugador.getBarcoSeleccionado());

            // Cambiar botones sellecionados hacerlo despues en el css o si no con una metodo
            btnBarco1.setStyle(boton == btnBarco1 ? "-fx-border-color: #00FF00; -fx-border-width: 3;" : "");
            btnBarco2.setStyle(boton == btnBarco2 ? "-fx-border-color: #00FF00; -fx-border-width: 3;" : "");
            btnBarco3.setStyle(boton == btnBarco3 ? "-fx-border-color: #00FF00; -fx-border-width: 3;" : "");

        } else if (barcoSeleccionar != null && !barcoSeleccionar.getEstaVivo()) {
            lbDatosCombate.setText(barcoSeleccionar.getBarco().getNombre() + " está hundido y no puede ser seleccionado.");
        }

    }

    private void actualizarBotonesAtaque(EstadoBarcoBatalla barco) {
        // Actualizamos los movimientos en funcion de su disponibilidad
        barco.actualizarMovimientosTurno();
        ArrayList<Movimiento> movimientos = barco.getMovimientos(); // Nota: O podrías usar getMovimientosTurno() si lo implementas

        // Creamos una lista de botones para iterar fácilmente
        Button[] botonesAtaque = {btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4};

        // Reseteamos todos los botones primero
        for (Button btn : botonesAtaque) {
            btn.setText("");
            btn.setDisable(true);
        }

        // Asignamos los movimientos a los botones
        for (int i = 0; i < movimientos.size() && i < 4; i++) {
            Movimiento mov = movimientos.get(i);
            if (mov != null) {
                botonesAtaque[i].setText(mov.getNombre());
                botonesAtaque[i].setDisable(false); // Habilitamos el botón
            }
        }
    }
   
    @FXML
    private void realizarAtaque(ActionEvent event) {
        if (controladorJugador.getBarcoSeleccionado() == null) {
            lbDatosCombate.setText("Debes seleccionar un barco");
            return;
        }
        if (batalla.getTurno() != 1) {
            return;
        }

        Button boton = (Button) event.getSource();
        ArrayList<Movimiento> movimientos = controladorJugador.getBarcoSeleccionado().getMovimientos();
        Movimiento movimientoUsado = null;

        if (boton == btnAtaque1 && movimientos.size() > 0) {
            movimientoUsado = movimientos.get(0);
        } else if (boton == btnAtaque2 && movimientos.size() > 1) {
            movimientoUsado = movimientos.get(1);
        } else if (boton == btnAtaque3 && movimientos.size() > 2) {
            movimientoUsado = movimientos.get(2);
        } else if (boton == btnAtaque4 && movimientos.size() > 3) {
            movimientoUsado = movimientos.get(3);
        }

        if (movimientoUsado != null) {
            // 3. Ejecutar el ataque

            actualizarUI();

            // 5. Comprobar si la IA ha perdido
            if (controladorIA.getPrimerVivo() == null) {
                lbDatosCombate.setText("¡Has hundido todos los barcos enemigos! ¡VICTORIA!");
                // Aquí podrías deshabilitar botones y mostrar una pantalla de victoria.
                return;
            }

            // 6. Iniciar el turno de la IA después de un breve retraso
            ejecutarTurnoIA();
        }
    }

    private void ejecutarTurnoIA() {
        // Deshabilitamos los botones del jugador para que no pueda actuar durante el turno de la IA
        btnAtaque1.setDisable(true);
        btnAtaque2.setDisable(true);
        btnAtaque3.setDisable(true);
        btnAtaque4.setDisable(true);

        // Usamos una pausa para que el turno de la IA no sea instantáneo
    }
    
    
//       private void iniciarContador() {
//        Timeline timeline = new Timeline(
//            new KeyFrame(Duration.seconds(1), e -> {
//                contador--;
//                if (contador >= 0) {
//                    label.setText(String.valueOf(contador));
//                }
//                if (contador == 0) {
//                    label.setText("¡Tiempo terminado!");
//                }
//            })
//        );
//        timeline.setCycleCount(31); // de 30 a 0 incluye 31 pasos
//        timeline.play();
//    }

}
