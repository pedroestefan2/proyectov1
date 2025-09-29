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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class FXML_PrincipalController implements Initializable,ControladorConUsuario {

    private Usuario usuarioPrincipal;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label UsernamePrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //si pones algo aqui peta
        // TODO
    }

    @FXML
    private void mostrarBattlePane(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AbrirVentanas.cambiarVentana(usuarioPrincipal, AbrirVentanas.INICIO_PRINCIPAL_JUGAR, stage);

    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioPrincipal = usuario;
        if (usuario != null && UsernamePrincipal != null) {
            UsernamePrincipal.setText(usuario.getNombreUsuario());
        }
    }

}
