package com.example.cv_builder.util;

import com.example.cv_builder.controllers.PreviewController;
import com.example.cv_builder.controllers.TempStore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage mainStage;

    public static void setStage(Stage stage) { mainStage = stage; }

    public static void switchToHome() { switchSimple("/com/example/cv_builder/home-view.fxml"); }
    public static void switchToForm() { switchSimple("/com/example/cv_builder/form-view.fxml"); }
    public static void switchToPreview() {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/cv_builder/preview-view.fxml"));
            Parent root = loader.load();
            PreviewController previewController = loader.getController();
            // use TempStore CV (may be transient)
            previewController.setCV(TempStore.get());
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void switchToSaved() { switchSimple("/com/example/cv_builder/saved-view.fxml"); }

    private static void switchSimple(String fxml) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxml));
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // convenience to open preview with explicit CV (not necessary but kept)
    public static void openPreviewWith(com.example.cv_builder.model.CV cv) {
        TempStore.set(cv);
        switchToPreview();
    }
}
