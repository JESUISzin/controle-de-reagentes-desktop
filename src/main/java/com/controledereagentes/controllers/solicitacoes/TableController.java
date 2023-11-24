package com.controledereagentes.controllers.solicitacoes;

import com.controledereagentes.Main;
import com.controledereagentes.dao.SolicitacoesDAO;
import com.controledereagentes.interfaces.UpdateTableListener;
import com.controledereagentes.models.SolicitacaoModel;
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
    private TableView<SolicitacaoModel> solicitacoesTable;
    @FXML
    private TableColumn<SolicitacaoModel, Integer> id;
    @FXML
    private TableColumn<SolicitacaoModel, String> status;
    @FXML
    private TableColumn<SolicitacaoModel, String> comentario;
    @FXML
    private TableColumn<SolicitacaoModel, String> nome;
    @FXML
    private TableColumn<SolicitacaoModel, String> createdAt;
    @FXML
    private Button newButton;
    ObservableList<SolicitacaoModel> list;

    public TableController() {
    }

    @Override
    public void onUpdateTable() {
        updateTable();
    }

    public void getLotes() {
        this.list = new SolicitacoesDAO().list();
    }

    public void updateTable() {
        this.getLotes();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        comentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        solicitacoesTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();

        solicitacoesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SolicitacaoModel selectedReagente = solicitacoesTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/solicitacoes/edit.fxml"));
                Stage stage = createDialog("Editar Solicitação", fxmlLoader);
                EditController editController = fxmlLoader.getController();
                editController.setUpdateTableListener(this);
                editController.setAttributes(selectedReagente);
                Objects.requireNonNull(stage).showAndWait();
            }
        });
    }

    public void onNewSolicitacaoButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialogs/solicitacoes/new.fxml"));
        Stage stage = createDialog("Incluir Nova Solicitação", fxmlLoader);
        NewController newController = fxmlLoader.getController();
        newController.setUpdateTableListener(this);
        Objects.requireNonNull(stage).showAndWait();
    }
}
