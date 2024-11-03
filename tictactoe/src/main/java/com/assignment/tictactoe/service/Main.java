package com.assignment.tictactoe.service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage)  {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/tictactoe.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe");
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to load FXML file or initialize the application: " + e.getMessage());
        }
    }
}
