/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectov1.controllers.fxml;

import com.mycompany.proyectov1.Utils.AbrirVentanas;
import com.mycompany.proyectov1.interfaces.ControladorConUsuario;
import com.mycompany.proyectov1.models.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class FXML_PrincipalJugarController implements Initializable, ControladorConUsuario {

    private Usuario usuarioPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private Label UsernamePrincipal;

    @FXML
    public void CargarInicio(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AbrirVentanas.cambiarVentana(usuarioPrincipal, AbrirVentanas.INICIO_PRINCIPAL, stage);
        AbrirVentanas.abrirNuevaVentana(usuarioPrincipal, AbrirVentanas.INICIO_PRINCIPAL);

    }

    @FXML
    public void CargarBattlePaneMaquina(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AbrirVentanas.cambiarVentana(usuarioPrincipal, AbrirVentanas.INICIO_BATALLA_MAQUINA, stage);

    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioPrincipal = usuario;
        if (usuario != null && UsernamePrincipal != null) {
            UsernamePrincipal.setText(usuario.getNombreUsuario());
        }
    }

}
