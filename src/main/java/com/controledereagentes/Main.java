package com.controledereagentes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    String css = this.getClass().getResource("styles.css").toExternalForm();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app-view.fxml"));
        primaryStage.setTitle("Controle de Reagentes");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("assets/icon.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}