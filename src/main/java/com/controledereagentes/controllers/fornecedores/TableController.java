package com.controledereagentes.controllers.fornecedores;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.Main;
import com.controledereagentes.dao.FornecedorDAO;
import com.controledereagentes.models.FornecedorModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

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
    private final ConnectionFactory connection;

    public TableController() {
        this.connection = new ConnectionFactory();
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getFornecedores() {
        Connection conSql = connection.getConnection();
        this.list = new FornecedorDAO(conSql).list();
    }

    public void updateTable() {
        this.getFornecedores();
        id.setCellValueFactory(new PropertyValueFactory<FornecedorModel, Integer>("id"));
        razao_social.setCellValueFactory(new PropertyValueFactory<FornecedorModel, String>("razao_social"));
        cnpj.setCellValueFactory(new PropertyValueFactory<FornecedorModel, String>("cnpj"));
        fornecedoresTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getFornecedores();
        this.updateTable();

        fornecedoresTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FornecedorModel selectedFornecedor = fornecedoresTable.getSelectionModel().getSelectedItem();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/fornecedores/edit.fxml"));
                    Parent editFornecedor = fxmlLoader.load();
                    Scene scene = new Scene(editFornecedor);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Editar Fornecedor");
                    stage.setResizable(false);

                    EditController newController = fxmlLoader.getController();
                    newController.setUpdateTableListener(this);

                    newController.setAttributes(selectedFornecedor);

                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onNewFornecedoresButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/fornecedores/new.fxml"));
            Parent newFornecedor = fxmlLoader.load();
            Scene scene = new Scene(newFornecedor);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Incluir Novo Fornecedor");
            stage.setResizable(false);

            NewController newController = fxmlLoader.getController();
            newController.setUpdateTableListener(this);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
