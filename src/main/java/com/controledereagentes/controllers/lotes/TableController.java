package com.controledereagentes.controllers.lotes;

import com.controledereagentes.Main;
import com.controledereagentes.dao.LotesDAO;
import com.controledereagentes.interfaces.UpdateTableListener;
import com.controledereagentes.models.LoteModel;
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
    private TableView<LoteModel> lotesTable;
    @FXML
    private TableColumn<LoteModel, Integer> id;
    @FXML
    private TableColumn<LoteModel, Integer> numero;
    @FXML
    private TableColumn<LoteModel, Integer> itens_vinculados;
    @FXML
    private TableColumn<LoteModel, String> createdAt;
    @FXML
    private Button newButton;
    ObservableList<LoteModel> list;

    public TableController() {
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getLotes() {
        this.list = new LotesDAO().list();
    }

    public void updateTable() {
        this.getLotes();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        itens_vinculados.setCellValueFactory(new PropertyValueFactory<>("itens_vinculados"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        lotesTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();

        lotesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                LoteModel selectedReagente = lotesTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/lotes/edit.fxml"));
                Stage stage = createDialog("Editar Lote", fxmlLoader);
                EditController editController = fxmlLoader.getController();
                editController.setUpdateTableListener(this);
                editController.setAttributes(selectedReagente);
                Objects.requireNonNull(stage).showAndWait();
            }
        });
    }

    public void onNewLoteButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/lotes/new.fxml"));
        Stage stage = createDialog("Incluir Novo Lote", fxmlLoader);
        NewController newController = fxmlLoader.getController();
        newController.setUpdateTableListener(this);
        Objects.requireNonNull(stage).showAndWait();
    }
}
