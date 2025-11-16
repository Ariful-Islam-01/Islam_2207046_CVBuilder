package com.example.cv_builder.controllers;

import com.example.cv_builder.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button createBtn;

    @FXML
    public void initialize() {
        createBtn.setOnAction(e -> SceneManager.switchToForm());
    }
}
