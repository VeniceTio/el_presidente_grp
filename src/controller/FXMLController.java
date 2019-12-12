package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();

        GameView.getInstance().start("Farès");
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
    }
    @FXML
    private void scenarioObjectiveClick(MouseEvent event) {
        ImageView pic = (ImageView)event.getSource();
        AnchorPane parent = (AnchorPane)pic.getParent();
        parent.getChildren().get(3).setStyle("-fx-opacity: 0.5");
        pic.setStyle("-fx-opacity: 1");
    }

    public FXMLController() {}

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }
}
