package me.kano.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.kano.app.conf.NoteConfig;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("All")
public class NoteEditorController extends SceneController implements Initializable {

    @FXML private HBox buttons;
    @FXML private TextField title;
    @FXML private TextArea text;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        if (NoteConfig.getInstance().getIndex() != null) {
            final JSONObject componentObject = (JSONObject) NoteConfig.getInstance().getJsonArray().get(NoteConfig.getInstance().getIndex());
            title.setText(componentObject.get("title").toString());
            final JSONObject lines = (JSONObject) componentObject.get("lines");
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lines.size(); i++) sb.append(lines.get(String.valueOf(i+1)) + "\n");
            text.setText(sb.toString());
            final Button deleteBtn = new Button("delete");
            deleteBtn.setPrefSize(112,41);
            deleteBtn.setOnMouseClicked(event -> {
                NoteConfig.getInstance().getJsonArray().remove(componentObject);
                NoteConfig.getInstance().loadData();
                onCloseEditor(event);
            });
            buttons.getChildren().add(1, deleteBtn);
        }
    }

    @FXML public void onSubmitTodo(MouseEvent event) {
        if ((title.getText().isEmpty() || title.getText().isBlank() ) &&
                (text.getText().isEmpty() || text.getText().isBlank())) return;
        final JSONObject todoObject = new JSONObject();
        final JSONObject lines = new JSONObject();
        final String[] texts = text.getText().split("\n");
        for (int i = 0; i < texts.length; i++) lines.put(String.valueOf(i+1), texts[i]);
        todoObject.put("title", title.getText());
        todoObject.put("lines", lines);
        if (NoteConfig.getInstance().getIndex() == null) NoteConfig.getInstance().getJsonArray().add(todoObject);
        else NoteConfig.getInstance().getJsonArray().set(NoteConfig.getInstance().getIndex(), todoObject);
        NoteConfig.getInstance().loadData();
        title.clear(); text.clear();
        onCloseEditor(event);
    }

    public void onCloseEditor(MouseEvent event) {
        closeEditor((Stage) ((Button) event.getSource()).getScene().getWindow());
    }
}