package me.kano.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

@SuppressWarnings("ALL")
public class NoteApp extends Application {

    private double x, y;

    @Override public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app-view.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Todolist app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}