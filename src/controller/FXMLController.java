package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DeadEnd;
import model.RepRecEnd8;
import view.GameView;
import view.OptionsView;

public class FXMLController implements Initializable
{
    /**
     * Méthode déterminant l'action à réaliser lorsque le bouton de fermeture est cliqué
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Méthode déterminant l'action à réaliser lorsque le bouton "Jouer" est cliqué
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void playButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();

        OptionsView.getInstance().start();
    }

    /**
     * Méthode déterminant l'action à réaliser lorsque le bouton "Lancer la partie" est cliqué
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void startGameButtonAction(ActionEvent event) throws Exception {
        Button btn = (Button)event.getSource();
        AnchorPane parent = (AnchorPane)btn.getParent();
        TextField tf = (TextField)parent.getChildren().get(2);

        // On s'assure qu'un nom de joueur ait été entré et qu'un scénario ait été choisi, sinon rien ne se passe
        if(tf.getText().length() >= 3 && tf.getText().length() < 15 && ElementControl.getInstance().getEnd() != null) {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
            GameView.getInstance().start(tf.getText());
        }
    }

    /**
     * Méthode déterminant l'action à réaliser lorsque le bouton "Prochain semestre" est cliqué
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void nextSemesterButtonAction(ActionEvent event) {
        ElementControl.getInstance().ClockForvard();
    }

    /**
     * Méthode déterminant l'action à réaliser lorsque le joueur clique sur le scénario "Mort subite"
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void scenarioDeathClick(MouseEvent event) {
        // Aspect visuel de la sélection
        ImageView pic = (ImageView)event.getSource();
        AnchorPane parent = (AnchorPane)pic.getParent();
        parent.getChildren().get(4).setStyle("-fx-opacity: 0.5");
        pic.setStyle("-fx-opacity: 1");

        // On définit la manière de déterminer la fin de partie
        ElementControl.getInstance().setEnd(new DeadEnd());
    }

    /**
     * Méthode déterminant l'action à réaliser lorsque le joueur clique sur le scénario "Objectif"
     * @param event Evènement s'étant déclenché
     */
    @FXML
    private void scenarioObjectiveClick(MouseEvent event) {
        // Aspect visuel de la sélection
        ImageView pic = (ImageView)event.getSource();
        AnchorPane parent = (AnchorPane)pic.getParent();
        parent.getChildren().get(3).setStyle("-fx-opacity: 0.5");
        pic.setStyle("-fx-opacity: 1");

        // On définit la manière de déterminer la fin de partie
        ElementControl.getInstance().setEnd(new RepRecEnd8());
    }

    /**
     * Constructeur du controleur
     */
    public FXMLController() {}

    /**
     * Méthode à implémenter pour l'interface Itializable (JavaFX)
     * @param url Emplacement
     * @param rb Ressources
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) { }
}
