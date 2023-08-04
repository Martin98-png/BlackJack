package com.example.ud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//Version 1.2.0 - 8/3/2023
//Now double button works when the total of cards is 9,10 or 11 without aces and 16,17 or 18 with aces
//Bug fix - When a 10 and an ace is drawn the totalpoints were 20 instead of 21
//configureWinCondition method to avoid code repetition
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