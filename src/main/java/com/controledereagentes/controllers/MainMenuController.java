package com.controledereagentes.controllers;

import com.controledereagentes.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private VBox centerVBox;

    @FXML
    protected void onFornecedoresButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tables/fornecedores.fxml"));
            Parent fornecedoresContent = fxmlLoader.load();
            centerVBox.getChildren().setAll(fornecedoresContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNfesButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tables/nfes.fxml"));
            Parent nfesContent = fxmlLoader.load();
            centerVBox.getChildren().setAll(nfesContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLotesButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tables/lotes.fxml"));
            Parent lotesContent = fxmlLoader.load();
            centerVBox.getChildren().setAll(lotesContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onReagentesButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tables/reagentes.fxml"));
            Parent reagentesContent = fxmlLoader.load();
            centerVBox.getChildren().setAll(reagentesContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSolicitacoesButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tables/solicitacoes.fxml"));
            Parent solicitacoesContent = fxmlLoader.load();
            centerVBox.getChildren().setAll(solicitacoesContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onExitButtonClick() {
        try {
            Platform.exit();
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
