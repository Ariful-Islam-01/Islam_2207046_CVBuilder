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

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setHeaderText(null);
                a.setContentText("Your CV created successfully!");
                a.show();

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
        if (phoneField.getText().isEmpty()) {
            showAlert("Phone is required.");
            return false;
        }
        if (addressField.getText().isEmpty()) {
            showAlert("Address is required.");
            return false;
        }
        if (educationField.getText().isEmpty()) {
            showAlert("Education is required.");
            return false;
        }
        if (skillsField.getText().isEmpty()) {
            showAlert("Skills is required.");
            return false;
        }
        if (experienceField.getText().isEmpty()) {
            showAlert("Experience is required.");
            return false;
        }
        if (projectsField.getText().isEmpty()) {
            showAlert("Project is required.");
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
