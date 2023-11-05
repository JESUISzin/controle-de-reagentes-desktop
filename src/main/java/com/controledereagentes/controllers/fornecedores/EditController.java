package com.controledereagentes.controllers.fornecedores;

import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.FornecedorModel;
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
    public void setAttributes(FornecedorModel fornecedor) {
        id = fornecedor.getId();
        cnpj_input.setText(fornecedor.getCnpj());
        razao_social_input.setText(String.valueOf(fornecedor.getRazao_social()));
    }


    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        String cnpj = cnpj_input.getText();
        String razaoSocial = razao_social_input.getText();

        if (cnpj != null && !cnpj.isEmpty() && razaoSocial != null && !razaoSocial.isEmpty()) {
            FornecedorDAO fornecedorDAO = getFornecedorDAO();

            try {
                fornecedorDAO.update(this.id, cnpj, razaoSocial);
                updateTable();
                closeDialog(event);
            } catch (RuntimeException e) {
                errorMessage.setText("Não foi possível atualizar o item!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
            }
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

    // Método para excluir o item
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        FornecedorDAO fornecedorDAO = getFornecedorDAO();

        try {
            fornecedorDAO.delete(this.id);
            updateTable();
            closeDialog(event);
        } catch (RuntimeException e) {
            errorMessage.setText("Não é possível excluir este item!");
            errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
        }
    }
}
