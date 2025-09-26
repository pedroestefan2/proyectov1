/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectov1.controllers.fxml;

import com.mycompany.proyectov1.Utils.AbrirVentanas;
import com.mycompany.proyectov1.controllers.UsuarioPrincipalControlador;
import com.mycompany.proyectov1.dao.ComprobarUsuarioBD;
import com.mycompany.proyectov1.dao.RegistrarUsuarioBD;
import com.mycompany.proyectov1.models.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class FXML_StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane RegisterPane;
    @FXML
    private AnchorPane RecoverPane;
    //Loggin
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private TextField textUserName;
    @FXML
    private TextField passwordTextField1;
    @FXML
    private PasswordField hiddenPasswordTextField1;
    @FXML
    private CheckBox showPassword1;
    //Registro
    @FXML
    private TextField textUserNameReg;
    @FXML
    private TextField textFirstNameReg;
    @FXML
    private TextField textSecondNameReg;
    @FXML
    private TextField textCorreoReg;
    @FXML
    private TextField textTelfReg;
    @FXML
    private PasswordField hiddenPasswordTextField;

    @FXML
    private CheckBox chkAceptarTerminos;

    @FXML
    private void mostrarLogin() {
        LoginPane.setVisible(true);
    }

    //parte del login
    @FXML
    private void ocultarLogin() {
        LoginPane.setVisible(false);
    }

    @FXML
    private void borrarUsername() {

        // textUserNameReg.clear();
        limpiarEstiloCampo(textUserNameReg);
    }

    @FXML
    private void mostrarContraseña() {
        if (showPassword1.isSelected()) {
            passwordTextField1.setText(hiddenPasswordTextField1.getText());
            passwordTextField1.setVisible(true);
            hiddenPasswordTextField1.setVisible(false);
        } else {
            passwordTextField1.setVisible(false);
            hiddenPasswordTextField1.setVisible(true);
            passwordTextField1.setText("");
        }
    }

    @FXML
    private void validarUsuario(ActionEvent event) {
        String auxnombreusuario = textUserName.getText();
        String auxcontraseña = hiddenPasswordTextField1.getText();
        System.out.println("validando usuario" + auxnombreusuario + " " + auxcontraseña);
        Usuario usuario = ComprobarUsuarioBD.login(auxnombreusuario, auxcontraseña);
        if (usuario != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            AbrirVentanas.cambiarVentana(usuario,AbrirVentanas.INICIO_PRINCIPAL, stage);
        } else {
            if (ComprobarUsuarioBD.existeUsuario(auxnombreusuario)) {
                passwordTextField1.setVisible(true);
                passwordTextField1.setText("Contraseña incorrecta");
                System.out.println("cont invalida");
            } else {
                textUserName.setVisible(true);
                textUserName.setText("Usuario incorrecto");
                System.out.println("no existe");
            }
        }

    }

    @FXML
    private void abrirRecover() {
        RecoverPane.setVisible(true);
    }

    @FXML
    private void mostrarRegistro() {
        RegisterPane.setVisible(true);

    }

    //pate del registro
    @FXML
    private void ocultarRegistro() {
        RegisterPane.setVisible(false);

    }

    @FXML
    private void ocultarRegistromostrarLogin() {
        RegisterPane.setVisible(false);
        LoginPane.setVisible(true);

    }

    @FXML
    private void dejarRegistrarse() {

    }

    @FXML
    private void crearUsuario() {
        String auxuser = textUserNameReg.getText();
        String auxnombre = textFirstNameReg.getText();
        String auxapellido = textSecondNameReg.getText();
        String auxcorreo = textCorreoReg.getText();
        String auxtelf = textTelfReg.getText();
        String auxcontraseña = hiddenPasswordTextField.getText();
        boolean camposValidos = true;
        if (chkAceptarTerminos.isSelected()) {
            if (auxuser.isBlank() || auxnombre.isBlank() || auxapellido.isBlank() || auxcorreo.isBlank() || auxtelf.isBlank() || auxcontraseña.isBlank()) {
                System.out.println("Algunos Campos estan vacios");
            }
            // verificar duplicados
            if (ComprobarUsuarioBD.existeUsuario(auxuser)) {
                textUserNameReg.setText("Nombre de usuario ya existente");
                camposValidos = false;
            }

            if (ComprobarUsuarioBD.existeCorreo(auxcorreo)) {
                textCorreoReg.setText("Correo ya existente");
                camposValidos = false;
            }

            if (camposValidos) {
                boolean registrado = RegistrarUsuarioBD.registrarUsuario(auxuser, auxnombre, auxapellido, auxtelf, auxcorreo, auxcontraseña);

                if (registrado) {
                    System.out.println("Usuario registrado con éxito.");
                } else {
                    System.out.println("Error al registrar el usuario.");
                }

            }

        } else {
            System.out.println("Acepta las condiciones");
        }

    }

    // parte de recuperar contraseña
    @FXML
    private void ocultarRecovermostrarLogin() {
        RecoverPane.setVisible(false);
        LoginPane.setVisible(true);

    }

    private void limpiarEstiloCampo(TextField campo) {
        campo.setStyle("");
        campo.setText("");
    }

}
