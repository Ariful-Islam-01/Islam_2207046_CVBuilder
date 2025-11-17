package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;
import com.example.cv_builder.util.SceneManager;
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

    @FXML
    public void initialize() {

        generateBtn.setOnAction(e -> {
            if (isValid()) {

                CV cv = new CV();  // creates global instance
                cv.setData(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        educationField.getText(),
                        skillsField.getText(),
                        projectsField.getText(),
                        experienceField.getText()
                );

                SceneManager.switchToPreview();
            }
        });
    }

    private boolean isValid() {
        if (nameField.getText().isEmpty()) {
            showAlert("Full Name is required.");
            return false;
        }
        if (emailField.getText().isEmpty()) {
            showAlert("Email is required.");
            return false;
        }
        return true;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Field");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
