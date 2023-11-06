package com.controledereagentes.controllers.fornecedores;

import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.models.DialogModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.math.BigInteger;

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
        BigInteger cnpj;
        String cnpjText = cnpj_input.getText();
        if (!cnpjText.isEmpty()) {
            try {
                cnpj = new BigInteger(cnpjText);
            } catch (NumberFormatException e) {
                errorMessage.setText("CNPJ inválido!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }
        } else {
            errorMessage.setText("Preencha todos os campos!");
            errorMessage.setTextFill(Color.WHITE);
            return;
        }

        String razao_social = razao_social_input.getText();

        if (razao_social != null && !razao_social.isEmpty()) {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.add(cnpj, razao_social);

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
