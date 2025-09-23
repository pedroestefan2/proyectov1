/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1;

import com.mycompany.proyectov1.Utils.AbrirVentanas;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author pedro
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AbrirVentanas.abrirAplicacion();
    }

    public static void main(String[] args) {
        launch();
    }
}
