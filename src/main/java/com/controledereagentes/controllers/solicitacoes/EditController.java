package com.controledereagentes.controllers.solicitacoes;

import com.controledereagentes.dao.SolicitacoesDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.SolicitacaoModel;
import com.controledereagentes.models.StatusModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditController extends DialogModel {
    private Integer id;
    @FXML
    private TextField comentario_input;
    @FXML
    private ComboBox<StatusModel> id_status_input;
    @FXML
    private Label errorMessage;

    // Construtor padrão. Não passar parâmetros!!
    public EditController() {
    }

    // Método de inicialização
    public void initialize() {
        ObservableList<StatusModel> status = FXCollections.observableArrayList();
        status.add(new StatusModel(1, "Em análise"));
        status.add(new StatusModel(2, "Incompleto"));
        status.add(new StatusModel(3, "Concluído"));
        id_status_input.setItems(status);
    }

    // Define os atributos
    public void setAttributes(SolicitacaoModel solicitacao) {
        id = solicitacao.getId();
        comentario_input.setText(String.valueOf(solicitacao.getComentario()));
        StatusModel status = new StatusModel(solicitacao.getStatusId(), solicitacao.getStatus());
        id_status_input.setValue(status);
    }

    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        String comentarioText = comentario_input.getText();

        if (!comentarioText.isEmpty()) {

            int id_status;
            if (id_status_input.getValue() != null) {
                id_status = id_status_input.getValue().getId();
            } else {
                errorMessage.setText("Selecione uma unidade de medida!");
                errorMessage.setTextFill(Color.WHITE);
                return;
            }

            SolicitacoesDAO solicitacoesDAO = new SolicitacoesDAO();
            solicitacoesDAO.update(id, id_status, comentarioText);

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
        SolicitacoesDAO solicitacoesDAO = new SolicitacoesDAO();

        try {
            solicitacoesDAO.delete(this.id);
            updateTable();
            closeDialog(event);
        } catch (RuntimeException e) {
            errorMessage.setText("Não é possível excluir este item!");
            errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
        }
    }
}

