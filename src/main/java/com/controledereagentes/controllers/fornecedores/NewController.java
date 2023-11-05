package com.controledereagentes.controllers.fornecedores;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.models.DialogModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;

public class NewController extends DialogModel {
    @FXML
    private TextField cnpj_input;
    @FXML
    private TextField razao_social_input;
    @FXML
    private Label errorMessage;

    public NewController() {
    }

    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        String cnpj = cnpj_input.getText();
        String razaoSocial = razao_social_input.getText();

        if (cnpj != null && !cnpj.isEmpty() && razaoSocial != null && !razaoSocial.isEmpty()) {
            FornecedorDAO fornecedorDAO = getFornecedorDAO();
            fornecedorDAO.add(razaoSocial, cnpj);

            updateTable();
            closeDialog(event);
        } else {
            errorMessage.setText("Preencha todos os campos!");
            errorMessage.setTextFill(Color.WHITE);
        }
    }

    // Método do botão cancelar para sair da janela
    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        closeDialog(event);
    }
}
