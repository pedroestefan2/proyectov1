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
import com.mycompany.proyectov1.models.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private Usuario IA;
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

    @FXML
    private void CambiarBarcoSelecionado() {

    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioPrincipal = ComprobarUsuarioBD.BuscarUsuarioMazoNombre(usuario.getNombreUsuario());

        //ia
        this.usuarioIA = ComprobarUsuarioBD.BuscarUsuarioCompletoNombre("Magallanes");
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

        //vida de los barcos
        EstadoBarcoBatalla barcoActivoJugador = controladorJugador.getPrimerVivo();
        EstadoBarcoBatalla barcoActivoIA = controladorIA.getPrimerVivo();

        if (barcoActivoJugador != null) {
            lbUsuario1Vida.setText("Vida: " + barcoActivoJugador.getVidaActual());
        } else {
            lbUsuario1Vida.setText("Todos los barcos hundidos");
            // Lógica de fin de partida (derrota)
        }

        if (barcoActivoIA != null) {
            lbUsuario2Vida.setText("Vida: " + barcoActivoIA.getVidaActual());
        } else {
            lbUsuario2Vida.setText("Todos los barcos hundidos");
            // Lógica de fin de partida (victoria)
        }

    }

}
