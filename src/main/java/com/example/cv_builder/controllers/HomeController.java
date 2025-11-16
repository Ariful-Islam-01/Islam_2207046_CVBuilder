package com.example.cv_builder.controllers;

import com.example.cv_builder.util.SceneManager;
import javafx.fxml.FXML;

public class HomeController {

    // FXML-injected field optional; not required when using onAction in FXML,
    // but keeping it is fine:
    // @FXML private javafx.scene.control.Button createBtn;

    @FXML
    private void handleCreateCV() {
        // quick console debug to be certain the button click fires
        System.out.println("Create New CV button clicked -> switching to form");
        SceneManager.switchToForm();
    }
}
