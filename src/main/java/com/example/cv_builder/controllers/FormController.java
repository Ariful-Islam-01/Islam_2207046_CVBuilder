package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;
import com.example.cv_builder.service.CVService;
import com.example.cv_builder.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FormController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea educationField;
    @FXML private TextArea skillsField;
    @FXML private TextArea experienceField;
    @FXML private TextArea projectsField;
    @FXML private Button generateBtn;
    @FXML private Button saveBtn;
    @FXML private Button backBtn;

    private final CVService service = AppServices.getCvService();
    private CV editing;

    @FXML
    public void initialize() {
        editing = TempStore.get();
        if (editing != null) {
            loadIntoForm(editing);
            TempStore.clear();
        }

        generateBtn.setOnAction(e -> {
            if (isValid()) {
                CV cv = collectFromForm();
                TempStore.set(cv);
                SceneManager.switchToPreview();
            }
        });

        saveBtn.setOnAction(e -> {
            if (!isValid()) return;
            CV cv = collectFromForm();
            if (editing != null) cv.setId(editing.getId());
            saveBtn.setDisable(true);
            service.save(cv).whenComplete((saved, ex) -> {
                Platform.runLater(() -> saveBtn.setDisable(false));
                if (ex != null) {
                    ex.printStackTrace();
                    Platform.runLater(() -> showAlert("Save failed: " + ex.getMessage()));
                } else {
                    Platform.runLater(() -> {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "CV saved successfully", ButtonType.OK);
                        a.showAndWait();
                    });
                }
            });
        });

        backBtn.setOnAction(e -> SceneManager.switchToHome());
    }

    private CV collectFromForm() {
        return new CV(
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                addressField.getText().trim(),
                educationField.getText().trim(),
                skillsField.getText().trim(),
                projectsField.getText().trim(),
                experienceField.getText().trim()
        );
    }

    private void loadIntoForm(CV cv) {
        nameField.setText(cv.getName());
        emailField.setText(cv.getEmail());
        phoneField.setText(cv.getPhone());
        addressField.setText(cv.getAddress());
        educationField.setText(cv.getEducation());
        skillsField.setText(cv.getSkills());
        projectsField.setText(cv.getProjects());
        experienceField.setText(cv.getExperience());
    }

    private boolean isValid() {
        if (nameField.getText().isEmpty()) { showAlert("Full Name is required."); return false; }
        if (emailField.getText().isEmpty()) { showAlert("Email is required."); return false; }
        if (phoneField.getText().isEmpty()) { showAlert("Phone is required."); return false; }
        if (addressField.getText().isEmpty()) { showAlert("Address is required."); return false; }
        if (educationField.getText().isEmpty()) { showAlert("Education is required."); return false; }
        if (skillsField.getText().isEmpty()) { showAlert("Skills is required."); return false; }
        if (experienceField.getText().isEmpty()) { showAlert("Experience is required."); return false; }
        if (projectsField.getText().isEmpty()) { showAlert("Project is required."); return false; }
        return true;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
