package com.controledereagentes.controllers.nfes;

import com.controledereagentes.Main;
import com.controledereagentes.dao.NfeDAO;
import com.controledereagentes.interfaces.UpdateTableListener;
import com.controledereagentes.models.NfeModel;
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
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.controledereagentes.models.DialogModel.createDialog;

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

    public TableController() {
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getNfes() {
        this.list = new NfeDAO().list();
    }

    public void updateTable() {
        this.getNfes();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        data_emissao.setCellValueFactory(new PropertyValueFactory<>("data_emissao"));
        razao_social.setCellValueFactory(new PropertyValueFactory<>("razao_social"));
        cnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        nfesTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();

        nfesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                NfeModel selectedNfe = nfesTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/nfes/edit.fxml"));
                Stage stage = createDialog("Editar Nota Fiscal", fxmlLoader);
                EditController editController = fxmlLoader.getController();
                editController.setUpdateTableListener(this);
                editController.setAttributes(selectedNfe);
                Objects.requireNonNull(stage).showAndWait();
            }
        });
    }

    public void onNewNfeButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/nfes/new.fxml"));
        Stage stage = createDialog("Incluir Nova Nota Fiscal", fxmlLoader);
        NewController newController = fxmlLoader.getController();
        newController.setUpdateTableListener(this);
        Objects.requireNonNull(stage).showAndWait();
    }
}
