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

public class IntroView{
    private static IntroView _instance = null;

    public static IntroView getInstance() {
        if(_instance == null)
            _instance = new IntroView();

        return _instance;
    }
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/intro_scene.fxml"));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.show();
    }
}
