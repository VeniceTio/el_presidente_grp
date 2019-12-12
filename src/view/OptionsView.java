package view;

import controller.ElementControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Indicator;
import model.Lever;
import model.LeverFamily;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsView {
    private static OptionsView _instance = null;

    public static OptionsView getInstance() {
        if(_instance == null)
            _instance = new OptionsView();

        return _instance;
    }
    public void start() throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/options_scene.fxml"));

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.show();
    }
}