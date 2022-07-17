package me.kano.app.component;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import me.kano.app.conf.NoteConfig;
import me.kano.app.controller.SceneController;
import me.kano.app.controller.ViewController;
import org.json.simple.JSONObject;

public class BoxComponent extends VBox {

    public BoxComponent(ViewController controller, String t, Object t1) {
        final Text title = new Text(t);
        final VBox textBox = new VBox();
        final JSONObject lines = (JSONObject) t1;
        this.getStyleClass().add("todo-box");
        this.setSpacing(5);
        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(event -> {
            NoteConfig.getInstance().setIndex(controller.getListBox().getChildren().indexOf(this));
            SceneController.openEditor((Stage) ((VBox) event.getSource()).getScene().getWindow());
        });
        setMargin(title, new Insets(10,0,0,20));

        title.setFill(Paint.valueOf("White"));
        title.setOpacity(0.7);
        title.setFont(Font.font("Arial Bold", 18));

        for (int i = 0; i < lines.size(); i++) {
            final Text text = new Text(lines.get(String.valueOf(i+1)).toString());
            final TextFlow textFlow = new TextFlow(text);
            text.setFont(Font.font("Arial", 16));
            text.setFill(Color.WHITE);
            text.setOpacity(0.6);
            textBox.setSpacing(3);
            textBox.setPadding(new Insets(0,5,5,30));
            textBox.getChildren().add(textFlow);
        }
        this.getChildren().add(title);
        this.getChildren().add(textBox);
    }

}