package com.example.cv_builder;

import com.example.cv_builder.util.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SceneManager.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/cv_builder/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("CV Builder");
        stage.setScene(scene);
        stage.show();
    }
}
