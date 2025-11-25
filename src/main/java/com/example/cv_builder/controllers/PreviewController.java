package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;
import com.example.cv_builder.service.CVService;
import com.example.cv_builder.util.JsonUtil;
import com.example.cv_builder.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class PreviewController {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;
    @FXML private Label educationLabel;
    @FXML private Label skillsLabel;
    @FXML private Label experienceLabel;
    @FXML private Label projectsLabel;

    @FXML private Button backBtn;
    @FXML private Button saveBtn;
    @FXML private Button exportBtn;
    @FXML private Button editBtn;

    private CV cv;
    private final CVService service = AppServices.getCvService();

    @FXML
    private void initialize() {
        backBtn.setOnAction(e -> SceneManager.switchToForm());
        saveBtn.setOnAction(e -> {
            if (cv == null) return;
            saveBtn.setDisable(true);
            service.save(cv).whenComplete((saved, ex) -> {
                Platform.runLater(() -> saveBtn.setDisable(false));
                if (ex != null) {
                    ex.printStackTrace();
                    Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Save failed: " + ex.getMessage()).showAndWait());
                } else {
                    Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, "Saved", ButtonType.OK).showAndWait());
                }
            });
        });

        exportBtn.setOnAction(e -> {
            if (cv == null) return;
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Export CV to JSON");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            File file = chooser.showSaveDialog(null);
            if (file == null) return;
            try {
                JsonUtil.exportToFile(java.util.List.of(cv), file);
                new Alert(Alert.AlertType.INFORMATION, "Exported to " + file.getAbsolutePath()).show();
            } catch (IOException ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Export failed: " + ex.getMessage()).show();
            }
        });

        editBtn.setOnAction(e -> {
            if (cv == null) return;
            TempStore.set(cv);
            SceneManager.switchToForm();
        });
    }

    public void setCV(CV cv) {
        this.cv = cv;
        if (cv != null) loadData(cv);
    }

    private void loadData(CV cv) {
        nameLabel.setText(safe(cv.getName()));
        emailLabel.setText(safe(cv.getEmail()));
        phoneLabel.setText(safe(cv.getPhone()));
        addressLabel.setText(safe(cv.getAddress()));
        educationLabel.setText(safe(cv.getEducation()));
        skillsLabel.setText(safe(cv.getSkills()));
        experienceLabel.setText(safe(cv.getExperience()));
        projectsLabel.setText(safe(cv.getProjects()));
    }

    private String safe(String s) { return s == null ? "" : s; }
}
