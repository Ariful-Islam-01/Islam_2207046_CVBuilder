package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewController {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    @FXML private Label educationLabel;
    @FXML private Label skillsLabel;
    @FXML private Label experienceLabel;
    @FXML private Label projectsLabel;

    public void setCV(CV cv) {
        if (cv != null) loadData(cv);
    }

    private void loadData(CV cv) {
        nameLabel.setText(cv.getName());
        emailLabel.setText(cv.getEmail());
        phoneLabel.setText(cv.getPhone());
        addressLabel.setText(cv.getAddress());
        educationLabel.setText(cv.getEducation());
        skillsLabel.setText(cv.getSkills());
        experienceLabel.setText(cv.getExperience());
        projectsLabel.setText(cv.getProjects());
    }

    @FXML
    private void goBack() {
        com.example.cv_builder.util.SceneManager.switchToForm();
    }
}
