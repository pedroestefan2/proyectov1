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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pedro
 */
public class AbrirVentanas {

    public static final String INICIO_APLICACION = "/fmxls/FXML_Start.fxml";
    public static final String INICIO_PRINCIPAL = "/fmxls/FXML_Principal.fxml";
    public static final String INICIO_BATALLA_MAQUINA = "/fmxls/FXML_BattleMaquina.fxml";
    public static final String INICIO_BATALLA_JUGADOR = "/fmxls/FXML_BattlePlayerl.fxml";
    private static Stage stagePrincipal;

    public static void prueba() {

        try {
            FXMLLoader loader = new FXMLLoader(AbrirVentanas.class.getResource(INICIO_BATALLA_MAQUINA));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.setResizable(true);//rrr
            stage.setTitle("Aplicación - Trident of Steel");
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
            //stage.sizeToScene();  // Se ajusta al tamaño del contenido
            stage.setResizable(false); // Permite redimensionar
            //stage.initStyle(StageStyle.UNDECORATED); cambiaar stage

            stage.setTitle("Aplicación - Trident of Steel ");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AbrirVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void abrirPrincipal(javafx.event.ActionEvent event) {
        try {
            //obtiene la ventana y la cierra
            Stage stageActual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stageActual.close();
            //abre la nueva ventana
            FXMLLoader loader = new FXMLLoader(AbrirVentanas.class.getResource(INICIO_PRINCIPAL));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Aplicación - Trident of Steel ");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AbrirVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cambiarVentana(String rutaFXML,Stage stage) {
         try {
        FXMLLoader loader = new FXMLLoader(AbrirVentanas.class.getResource(rutaFXML));
        Parent root = loader.load();

        // Obtener el Stage desde el nodo que lanzó el evento
        

        // Reemplazar el contenido del Scene con el nuevo FXML
        stage.getScene().setRoot(root);

    } catch (IOException ex) {
        Logger.getLogger(AbrirVentanas.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
