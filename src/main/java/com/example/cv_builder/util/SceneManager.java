package com.example.cv_builder.util;

import com.example.cv_builder.controllers.PreviewController;
import com.example.cv_builder.model.CV;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchToForm() {
        switchSimple("/com/example/cv_builder/form-view.fxml");
    }

    public static void switchToPreview() {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(
                    "/com/example/cv_builder/preview-view.fxml"
            ));

            Parent root = loader.load();

            PreviewController previewController = loader.getController();
            previewController.setCV(CV.getInstance());

            mainStage.setScene(new Scene(root));
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void switchSimple(String fxml) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxml));
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
