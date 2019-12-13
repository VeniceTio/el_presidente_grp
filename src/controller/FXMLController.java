package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.DeadEnd;
import model.RepRecEnd8;
import view.GameView;
import view.OptionsView;

public class FXMLController implements Initializable
{
    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void playButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();

        OptionsView.getInstance().start();
    }
    @FXML
    private void startGameButtonAction(ActionEvent event) throws Exception {
        Button btn = (Button)event.getSource();
        AnchorPane parent = (AnchorPane)btn.getParent();
        TextField tf = (TextField)parent.getChildren().get(2);

        if(tf.getText().length() >= 3 && tf.getText().length() < 15 && ElementControl.getInstance().getEnd() != null) {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
            GameView.getInstance().start(tf.getText());
        }
    }

    @FXML
    private void nextSemesterButtonAction(ActionEvent event) {
        ElementControl.getInstance().ClockForvard();
    }

    @FXML
    private void scenarioDeathClick(MouseEvent event) {
        ImageView pic = (ImageView)event.getSource();
        AnchorPane parent = (AnchorPane)pic.getParent();
        parent.getChildren().get(4).setStyle("-fx-opacity: 0.5");
        pic.setStyle("-fx-opacity: 1");

        ElementControl.getInstance().setEnd(new DeadEnd());
    }
    @FXML
    private void scenarioObjectiveClick(MouseEvent event) {
        ImageView pic = (ImageView)event.getSource();
        AnchorPane parent = (AnchorPane)pic.getParent();
        parent.getChildren().get(3).setStyle("-fx-opacity: 0.5");
        pic.setStyle("-fx-opacity: 1");

        ElementControl.getInstance().setEnd(new RepRecEnd8());
    }

    public FXMLController() {}

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }
}
