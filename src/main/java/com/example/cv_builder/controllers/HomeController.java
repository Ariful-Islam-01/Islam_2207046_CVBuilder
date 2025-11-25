package com.example.cv_builder.controllers;

import com.example.cv_builder.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML private Button createBtn;
    @FXML private Button savedBtn;

    @FXML
    private void initialize() {
        // nothing required, handlers via FXML methods or set in FXML
    }

    @FXML
    private void handleCreateCV() {
        System.out.println("Create New CV clicked");
        SceneManager.switchToForm();
    }

    @FXML
    private void handleSavedCVs() {
        SceneManager.switchToSaved();
    }
}
