package com.example.cv_builder.util;

import com.example.cv_builder.controllers.PreviewController;
import com.example.cv_builder.model.CV;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchToHome() {
        switchSimple("/com/example/cv_builder/home-view.fxml");
    }

    public static void switchToForm() {
        switchSimple("/com/example/cv_builder/form-view.fxml");
    }

    public static void switchToPreview() {
        switchSimple("/com/example/cv_builder/preview-view.fxml");
    }

    public static void switchToSaved() {
        switchSimple("/com/example/cv_builder/saved-view.fxml");
    }

    private static void switchSimple(String fxml) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxml));
            mainStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // helper to open preview and pass CV instance (if more control needed)
    public static void openPreviewWith(CV cv) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/cv_builder/preview-view.fxml"));
            Parent root = loader.load();
            PreviewController controller = loader.getController();
            controller.setCV(cv);
            mainStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
