package me.kano.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.kano.app.NoteApp;

import java.io.IOException;

@SuppressWarnings("All")
public class SceneController {
    private static double x, y;
    public static void openEditor(Stage stage){
        try {
            Parent root = FXMLLoader.load(NoteApp.class.getResource("note-editor.fxml"));
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
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeEditor(Stage stage){
        try {
            Parent root = FXMLLoader.load(NoteApp.class.getResource("app-view.fxml"));
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
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMinimizeWindow(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML public void onCloseWindow(MouseEvent event){
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
