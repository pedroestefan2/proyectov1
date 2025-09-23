/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.Utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author pedro
 */
public class AbrirVentanas {

    public static final String INICIO_APLICACION = "/fmxls/login/FXML_Start.fxml";

    public static void prueba() {

        try {
            FXMLLoader loader = new FXMLLoader(AbrirVentanas.class.getResource(INICIO_APLICACION));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("Aplicación - Prueba");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AbrirVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void abrirAplicacion() {
        try {
            FXMLLoader loader = new FXMLLoader(AbrirVentanas.class.getResource(INICIO_APLICACION));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("Aplicación - Inicio ");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AbrirVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cambiarVentana(String rutaFXML) {

    }
}
