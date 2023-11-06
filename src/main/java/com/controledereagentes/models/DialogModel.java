package com.controledereagentes.models;

import com.controledereagentes.ApplicationManager;
import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.interfaces.UpdateTableListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;

public class DialogModel {
    public BorderPane window;
    private double xOffset = 0;
    private double yOffset = 0;
    private UpdateTableListener updateTableListener;
    private final ConnectionFactory connection;

    // Construtor dos diálogos
    public DialogModel() {
        this.connection = new ConnectionFactory();
    }

    // Método que define o listener na classe
    public void setUpdateTableListener(UpdateTableListener listener) {
        this.updateTableListener = listener;
    }

    // Método para atualizar a tabela
    protected void updateTable() {
        if (updateTableListener != null) {
            updateTableListener.onUpdateTable();
        }
    }

    // Método para rastrear o início do arraste
    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    // Método para rastrear o movimento do mouse e mover a janela
    public void onMouseDragged(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - xOffset);
        stage.setY(mouseEvent.getScreenY() - yOffset);
    }

    // Método para fechar a janela
    protected static void closeDialog(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Método que instância uma connection com o banco e a retorna
    public Connection getConnection() {
        Connection conSql = connection.getConnection();
        return conSql;
    }

    public static Stage createDialog(String title, FXMLLoader fxmlLoader) {
        try {
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.initOwner(ApplicationManager.getInstance().getPrimaryStage());
            stage.setScene(scene);
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
