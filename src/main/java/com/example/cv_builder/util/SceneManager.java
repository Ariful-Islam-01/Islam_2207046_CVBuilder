package com.example.cv_builder.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchToForm() {
        switchScene("/com/example/cv_builder/form-view.fxml");
    }

    private static void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            primaryStage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToPreview() {
        switchScene("/com/example/cv_builder/preview-view.fxml");
    }

}
