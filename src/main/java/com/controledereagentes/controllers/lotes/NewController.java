package com.controledereagentes.controllers.lotes;

import com.controledereagentes.dao.LotesDAO;
import com.controledereagentes.models.DialogModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class NewController extends DialogModel {
    @FXML
    private TextField numero_input;
    @FXML
    private Label errorMessage;

    public NewController() {
    }

    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        String numeroText = numero_input.getText();

        if (!numeroText.isEmpty()) {
            int numero;
            try {
                numero = Integer.parseInt(numeroText);
            } catch (NumberFormatException e) {
                errorMessage.setText("Número inválido!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }

            try {
                LotesDAO lotesDao = new LotesDAO();
                lotesDao.add(numero);
            } catch (RuntimeException e) {
                errorMessage.setText("Este lote já existe!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }

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
