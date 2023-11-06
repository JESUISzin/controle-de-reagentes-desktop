package com.controledereagentes.controllers.fornecedores;

import com.controledereagentes.Main;
import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.interfaces.UpdateTableListener;
import com.controledereagentes.models.FornecedorModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.controledereagentes.models.DialogModel.createDialog;

public class TableController implements Initializable, UpdateTableListener {
    @FXML
    private TableView<FornecedorModel> fornecedoresTable;
    @FXML
    private TableColumn<FornecedorModel, Integer> id;
    @FXML
    private TableColumn<FornecedorModel, String> razao_social;
    @FXML
    private TableColumn<FornecedorModel, String> cnpj;
    @FXML
    private Button newButton;
    ObservableList<FornecedorModel> list;

    public TableController() {
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getFornecedores() {
        this.list = new FornecedorDAO().list();
    }

    public void updateTable() {
        this.getFornecedores();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        razao_social.setCellValueFactory(new PropertyValueFactory<>("razao_social"));
        cnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        fornecedoresTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();

        fornecedoresTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FornecedorModel selectedFornecedor = fornecedoresTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/fornecedores/edit.fxml"));
                Stage stage = createDialog("Editar Fornecedor", fxmlLoader);
                EditController editController = fxmlLoader.getController();
                editController.setAttributes(selectedFornecedor);
                editController.setUpdateTableListener(this);
                Objects.requireNonNull(stage).showAndWait();
            }
        });
    }

    public void onNewFornecedoresButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/fornecedores/new.fxml"));
        Stage stage = createDialog("Incluir Novo Fornecedor", fxmlLoader);
        NewController newController = fxmlLoader.getController();
        newController.setUpdateTableListener(this);
        Objects.requireNonNull(stage).showAndWait();
    }
}
