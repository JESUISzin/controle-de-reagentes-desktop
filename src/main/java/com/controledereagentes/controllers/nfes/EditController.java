package com.controledereagentes.controllers.nfes;

import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.NfeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditController extends DialogModel {
    private Integer id;
    @FXML
    private TextField cnpj_input;
    @FXML
    private TextField razao_social_input;
    @FXML
    private Label errorMessage;

    // Construtor padrão. Não passar parâmetros!!
    public EditController() {
    }

    // Define os atributos
    public void setAttributes(NfeModel nfe) {
        id = nfe.getId();
        cnpj_input.setText(nfe.getCnpj());
        razao_social_input.setText(String.valueOf(nfe.getRazao_social()));
    }


    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        closeDialog(event);
    }

    // Método do botão cancelar para sair da janela
    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        closeDialog(event);
    }

    // Método para excluir o item
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        closeDialog(event);
    }
}
