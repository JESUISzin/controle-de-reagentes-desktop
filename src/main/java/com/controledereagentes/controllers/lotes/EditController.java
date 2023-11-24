package com.controledereagentes.controllers.lotes;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.dao.LotesDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.LoteModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditController extends DialogModel {
    private Integer id;
    private Integer itens_vinculados;
    @FXML
    private TextField numero_input;
    @FXML
    private Label errorMessage;
    private final ConnectionFactory connection;

    // Construtor padrão. Não passar parâmetros!!
    public EditController() {
        this.connection = new ConnectionFactory();
    }

    // Define os atributos
    public void setAttributes(LoteModel lote) {
        id = lote.getId();
        itens_vinculados = lote.getItens_vinculados();
        numero_input.setText(lote.getNumero().toString());
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
            LotesDAO lotesDao = new LotesDAO();
            lotesDao.update(this.id, numero, this.itens_vinculados);

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

    // Método para excluir o item
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        LotesDAO lotesDAO = new LotesDAO();

        try {
            lotesDAO.delete(this.id);
            updateTable();
            closeDialog(event);
        } catch (RuntimeException e) {
            errorMessage.setText("Não é possível excluir este item!");
            errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
        }
    }
}
