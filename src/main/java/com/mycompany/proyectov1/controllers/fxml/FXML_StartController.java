/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectov1.controllers.fxml;

import com.mycompany.proyectov1.controllers.UsuarioControladora;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private void validarUsuario() {
        String auxnombreusuario = textUserName.getText();
        String auxcontraseña = hiddenPasswordTextField1.getText();
        System.out.println("validando usuario" + auxnombreusuario + " " + auxcontraseña);
       String resultado= UsuarioControladora.validarUsuario(auxnombreusuario,auxcontraseña);
       if(resultado.equals("valido")){
           
       }else if(resultado.equals("con")){
           System.out.println("cont invalida");
       }else {
           System.out.println("no existe");
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
        
        if(chkAceptarTerminos.isSelected()){
            
            System.out.println("Creando usuario");
           String resultado= UsuarioControladora.guardarUsuario(auxuser,auxnombre,auxapellido,auxcorreo,auxtelf,auxcontraseña);
           if(resultado.equals("valido")){
               
           }else if(resultado.equals("invalido")){
               
           }else{
               System.out.println("Error desconocido");
           }
        }else{
            System.out.println("Acepta las condiciones");
        }
        
     
    }

    // parte de recuperar contraseña
    @FXML
    private void ocultarRecovermostrarLogin() {
        RecoverPane.setVisible(false);
        LoginPane.setVisible(true);

    }

}
