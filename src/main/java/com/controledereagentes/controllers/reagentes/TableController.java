package com.controledereagentes.controllers.reagentes;

import com.controledereagentes.Main;
import com.controledereagentes.dao.ReagentesDAO;
import com.controledereagentes.interfaces.UpdateTableListener;
import com.controledereagentes.models.ReagenteModel;
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
    private TableView<ReagenteModel> reagentesTable;

    @FXML
    private TableColumn<ReagenteModel, Integer> id;

    @FXML
    private TableColumn<ReagenteModel, Integer> cod;

    @FXML
    private TableColumn<ReagenteModel, String> descricao;

    @FXML
    private TableColumn<ReagenteModel, String> loc_estoque;

    @FXML
    private TableColumn<ReagenteModel, Float> estoque_atual;

    @FXML
    private TableColumn<ReagenteModel, String> un;

    @FXML
    private TableColumn<ReagenteModel, Float> vlr_estoque;

    @FXML
    private TableColumn<ReagenteModel, Integer> entradas;

    @FXML
    private TableColumn<ReagenteModel, Integer> saidas;

    @FXML
    private Button newButton;
    ObservableList<ReagenteModel> list;

    public TableController() {
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getReagentes() {
        this.list = new ReagentesDAO().list();
    }

    public void updateTable() {
        this.getReagentes();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        loc_estoque.setCellValueFactory(new PropertyValueFactory<>("loc_estoque"));
        estoque_atual.setCellValueFactory(new PropertyValueFactory<>("estoque_atual"));
        un.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        vlr_estoque.setCellValueFactory(new PropertyValueFactory<>("vlr_estoque"));
        entradas.setCellValueFactory(new PropertyValueFactory<>("entradas"));
        saidas.setCellValueFactory(new PropertyValueFactory<>("saidas"));
        reagentesTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();

        reagentesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ReagenteModel selectedReagente = reagentesTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/reagentes/edit.fxml"));
                Stage stage = createDialog("Editar Tipo", fxmlLoader);
                EditController editController = fxmlLoader.getController();
                editController.setUpdateTableListener(this);
                editController.setAttributes(selectedReagente);
                Objects.requireNonNull(stage).showAndWait();
            }
        });
    }

    public void onNewTipoButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/reagentes/new.fxml"));
        Stage stage = createDialog("Incluir Novo Tipo", fxmlLoader);
        NewController newController = fxmlLoader.getController();
        newController.setUpdateTableListener(this);
        Objects.requireNonNull(stage).showAndWait();
    }
}
