package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;
import com.example.cv_builder.service.CVService;
import com.example.cv_builder.util.JsonUtil;
import com.example.cv_builder.util.SceneManager;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SavedCVsController {

    @FXML private TableView<CV> table;
    @FXML private TableColumn<CV, Integer> idCol;
    @FXML private TableColumn<CV, String> nameCol;
    @FXML private TableColumn<CV, String> emailCol;
    @FXML private Button loadBtn;
    @FXML private Button deleteBtn;
    @FXML private Button exportBtn;
    @FXML private Button refreshBtn;
    @FXML private Button backBtn;

    private final CVService service = AppServices.getCvService();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId()));
        nameCol.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getName()));
        emailCol.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getEmail()));

        ObservableList<CV> list = service.getObservableList();
        table.setItems(list);

        refreshBtn.setOnAction(e -> loadAll());
        backBtn.setOnAction(e -> SceneManager.switchToHome());

        loadBtn.setOnAction(e -> {
            CV selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) { show("Please select a CV to load"); return; }
            TempStore.set(selected);
            SceneManager.switchToForm();
        });

        deleteBtn.setOnAction(e -> {
            CV selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) { show("Please select a CV to delete"); return; }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected CV?", ButtonType.YES, ButtonType.NO);
            confirm.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.YES) {
                    service.delete(selected).whenComplete((v, ex) -> {
                        if (ex != null) {
                            ex.printStackTrace();
                            Platform.runLater(() -> show("Delete failed: " + ex.getMessage()));
                        }
                    });
                }
            });
        });

        exportBtn.setOnAction(e -> {
            List<CV> selected = table.getSelectionModel().getSelectedItems();
            if (selected == null || selected.isEmpty()) { show("Select at least one CV to export"); return; }
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Export Selected CVs");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            File file = chooser.showSaveDialog(null);
            if (file == null) return;
            try {
                JsonUtil.exportToFile(selected, file);
                show("Exported to " + file.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                show("Export failed: " + ex.getMessage());
            }
        });

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadAll();
    }

    private void loadAll() {
        service.loadAll().exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() -> show("Failed to load saved CVs: " + ex.getMessage()));
            return null;
        });
    }

    private void show(String msg) {
        Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait());
    }
}
