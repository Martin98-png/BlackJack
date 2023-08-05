package com.example.ud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//Version 2.0 - 8/4/2023
//Author: Martin Velasquez
//Description: This program is a BlackJack game, the user can play against the CPU.
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Controller.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BlackJack<MartinVelasquez>");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}