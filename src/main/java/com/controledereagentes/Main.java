package com.controledereagentes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {

    String css = this.getClass().getResource("styles.css").toExternalForm();

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationManager.getInstance().setPrimaryStage(primaryStage);

        Parent root = FXMLLoader.load(getClass().getResource("app-view.fxml"));
        primaryStage.setTitle("Controle de Reagentes");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        setSecondaryScreen(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("assets/favicon.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setSecondaryScreen(Stage stage) { // Abre a tela no monitor secundário TODO remover depois
        Screen secondaryScreen = Screen.getScreens().get(1);
        if (secondaryScreen != null) {
            stage.setX(secondaryScreen.getVisualBounds().getMinX());
            stage.setY(secondaryScreen.getVisualBounds().getMinY());
        }
    }
}