package com.example.cv_builder;

import com.example.cv_builder.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.setStage(stage);
        SceneManager.switchToHome(); // initial scene
        stage.setTitle("CV Builder");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
