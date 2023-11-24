package com.controledereagentes.controllers.reagentes;

import com.controledereagentes.dao.ReagentesDAO;
import com.controledereagentes.models.DialogModel;
import com.controledereagentes.models.ReagenteModel;
import com.controledereagentes.models.UnModel;
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
    private TextField cod_input;
    @FXML
    private TextField descricao_input;
    @FXML
    private TextField loc_estoque_input;
    @FXML
    private ComboBox<UnModel> id_un_input;
    @FXML
    private Label errorMessage;

    // Construtor padrão. Não passar parâmetros!!
    public EditController() {
    }

    // Método de inicialização
    public void initialize() {
        ObservableList<UnModel> uns = FXCollections.observableArrayList();
        uns.add(new UnModel(1, "ml"));
        uns.add(new UnModel(2, "lt"));
        uns.add(new UnModel(3, "mg"));
        uns.add(new UnModel(4, "gr"));
        uns.add(new UnModel(5, "kg"));
        id_un_input.setItems(uns);
    }

    // Define os atributos
    public void setAttributes(ReagenteModel reagente) {
        id = reagente.getId();
        cod_input.setText(String.valueOf(reagente.getCod()));
        descricao_input.setText(reagente.getDescricao());
        loc_estoque_input.setText(reagente.getLoc_estoque());
        id_un_input.setValue(reagente.getUn());
    }

    // Método para confirmar a ação da janela
    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        String codText = cod_input.getText();
        String descricaoText = descricao_input.getText();
        String locEstoqueText = loc_estoque_input.getText();

        if (!codText.isEmpty() && !descricaoText.isEmpty() && !locEstoqueText.isEmpty()) {
            int cod;
            try {
                cod = Integer.parseInt(codText);
            } catch (NumberFormatException e) {
                errorMessage.setText("Número inválido!");
                errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
                return;
            }

            int id_un;
            if (id_un_input.getValue() != null) {
                id_un = id_un_input.getValue().getId();
            } else {
                errorMessage.setText("Selecione uma unidade de medida!");
                errorMessage.setTextFill(Color.WHITE);
                return;
            }

            try {
                ReagentesDAO reagentesDAO = new ReagentesDAO();
                reagentesDAO.update(id, cod, descricaoText, locEstoqueText, id_un);
            } catch (RuntimeException e) {
                errorMessage.setText("Este tipo já existe!");
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

    // Método para excluir o item
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        ReagentesDAO reagentesDAO = new ReagentesDAO();

        try {
            reagentesDAO.delete(this.id);
            updateTable();
            closeDialog(event);
        } catch (RuntimeException e) {
            errorMessage.setText("Não é possível excluir este item!");
            errorMessage.setTextFill(Color.rgb(255, 117, 117, 1));
        }
    }
}

