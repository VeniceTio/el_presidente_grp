package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ElementControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLController implements Initializable
{
    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void nextSemesterButtonAction(ActionEvent event) {
        ElementControl.getInstance().ClockForvard();
    }

    public FXMLController() {}

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }
}
