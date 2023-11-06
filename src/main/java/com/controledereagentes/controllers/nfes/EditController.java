package com.controledereagentes.controllers.nfes;

import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.dao.NfeDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.FornecedorModel;
import com.controledereagentes.models.NfeModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.time.format.DateTimeParseException;


public class EditController extends DialogModel {
    private Integer id;

    @FXML
    private TextField numero_input;
    @FXML
    private DatePicker data_emissao_input;
    @FXML
    private ComboBox<FornecedorModel> fornecedor_input;
    @FXML
    private Label errorMessage;

    // Construtor padrão. Não passar parâmetros!!
    public EditController() {
    }

    // Método de inicialização
    public void initialize() {
        ObservableList<FornecedorModel> fornecedores = new FornecedorDAO().list();
        fornecedor_input.setItems(fornecedores);
    }

    // Define os atributos
    public void setAttributes(NfeModel nfe) {
        id = nfe.getId();
        numero_input.setText(String.valueOf(nfe.getNumero()));
        data_emissao_input.setValue(nfe.getData_emissao());
        fornecedor_input.setValue(nfe.getFornecedor());
    }

    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        int numero;
        String numeroText = numero_input.getText();
        if (!numeroText.isEmpty()) {
            try {
                numero = Integer.parseInt(numeroText);
            } catch (NumberFormatException e) {
                errorMessage.setText("Número inválido!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }
        } else {
            errorMessage.setText("Preencha todos os campos!");
            errorMessage.setTextFill(Color.WHITE);
            return;
        }

        Date data_emissao;
        if (data_emissao_input.getValue() != null) {
            try {
                data_emissao = Date.valueOf(data_emissao_input.getValue());
            } catch (DateTimeParseException e) {
                errorMessage.setText("Data inválida!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }
        } else {
            errorMessage.setText("Data não foi preenchida!");
            errorMessage.setTextFill(Color.WHITE);
            return;
        }

        int fornecedor_id;
        if (fornecedor_input.getValue() != null) {
            fornecedor_id = fornecedor_input.getValue().getId();
        } else {
            errorMessage.setText("Selecione um fornecedor!");
            errorMessage.setTextFill(Color.WHITE);
            return;
        }

        if (numero != 0 && fornecedor_id != 0) {
            NfeDAO nfeDAO = new NfeDAO();

            try {
                nfeDAO.update(this.id, numero, data_emissao, fornecedor_id);
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
        NfeDAO nfeDAO = new NfeDAO();

        try {
            nfeDAO.delete(this.id);
            updateTable();
            closeDialog(event);
        } catch (RuntimeException e) {
            errorMessage.setText("Não é possível excluir este item!");
            errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
        }
    }
}
