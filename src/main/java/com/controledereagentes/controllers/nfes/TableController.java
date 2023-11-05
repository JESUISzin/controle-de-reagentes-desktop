package com.controledereagentes.controllers.nfes;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.Main;
import com.controledereagentes.dao.NfeDAO;
import com.controledereagentes.models.NfeModel;
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
import java.util.Date;
import java.util.ResourceBundle;

public class TableController implements Initializable, UpdateTableListener {
    @FXML
    private TableView<NfeModel> nfesTable;
    @FXML
    private TableColumn<NfeModel, Integer> id;
    @FXML
    private TableColumn<NfeModel, Integer> numero;
    @FXML
    private TableColumn<NfeModel, Date> data_emissao;
    @FXML
    private TableColumn<NfeModel, String> razao_social;
    @FXML
    private TableColumn<NfeModel, String> cnpj;
    @FXML
    private Button newButton;
    ObservableList<NfeModel> list;
    private final ConnectionFactory connection;

    public TableController() {
        this.connection = new ConnectionFactory();
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getNfes() {
        Connection conSql = connection.getConnection();
        this.list = new NfeDAO(conSql).list();
    }

    public void updateTable() {
        this.getNfes();
        id.setCellValueFactory(new PropertyValueFactory<NfeModel, Integer>("id"));
        numero.setCellValueFactory(new PropertyValueFactory<NfeModel, Integer>("numero"));
        data_emissao.setCellValueFactory(new PropertyValueFactory<NfeModel, Date>("data_emissao"));
        razao_social.setCellValueFactory(new PropertyValueFactory<NfeModel, String>("razao_social"));
        cnpj.setCellValueFactory(new PropertyValueFactory<NfeModel, String>("cnpj"));
        nfesTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.getNfes();
        this.updateTable();

        nfesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                NfeModel selectedNfe = nfesTable.getSelectionModel().getSelectedItem();
                System.out.println(selectedNfe);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/nfes/edit.fxml"));
                    Parent editNfe = fxmlLoader.load();
                    Scene scene = new Scene(editNfe);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Editar Nota Fiscal");
                    stage.setResizable(false);

                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onNewNfeButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/nfes/new.fxml"));
            Parent newNfe = fxmlLoader.load();
            Scene scene = new Scene(newNfe);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Incluir Nova Nota Fiscal");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
