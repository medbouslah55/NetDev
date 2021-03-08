/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mehdi
 */
public class Mainprog extends Application {
    
    public static Stage primaryStage = null;
    
    @Override
    public void start(Stage s) throws IOException {
        primaryStage=s;
        primaryStage.setTitle("MindSpace");
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/gui/ModifierStatus.fxml"));
        Scene scene = new Scene(root);
        
//        scene.getStylesheets().add(getClass().getResource("/gui/Design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
