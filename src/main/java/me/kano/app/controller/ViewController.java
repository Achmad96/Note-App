package me.kano.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.kano.app.component.BoxComponent;
import me.kano.app.conf.NoteConfig;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController extends SceneController implements Initializable {

    @FXML private VBox listBox;

    @Override public void initialize(URL location, ResourceBundle resources) {
        NoteConfig.getInstance().loadDataFile();
        if (NoteConfig.getInstance().getJsonArray().size() == 0){
            final Text text = new Text("No notes are available.");
            listBox.setAlignment(Pos.CENTER);
            text.setOpacity(0.6);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", 12));
            listBox.getChildren().add(text);
            return;
        }
        for (Object obj : NoteConfig.getInstance().getJsonArray()) {
            final JSONObject component = (JSONObject) obj;
            listBox.getChildren().add(new BoxComponent(this, component.get("title").toString(), component.get("lines")));
        }
    }

    @FXML public void onAddComponent(MouseEvent event) {
        NoteConfig.getInstance().setIndex(null);
        openEditor((Stage) ((TextField) event.getSource()).getScene().getWindow());
    }

    public VBox getListBox() {
        return listBox;
    }

}