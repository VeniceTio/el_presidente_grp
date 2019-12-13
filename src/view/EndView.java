package view;

import controller.ElementControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Indicator;
import model.IndicatorType;

import java.util.ArrayList;
import java.util.Arrays;
import view.Graphic;
public class EndView {
    /**
     * Instance de la vue
     */
    private static EndView _instance = null;

    /**
     * Méthode renvoyant l'instance de la vue
     * @return l'instance de la vue
     */
    public static EndView getInstance() {
        if(_instance == null) {
           _instance = new EndView();
        }
        return _instance;
    }

    ElementControl ec = ElementControl.getInstance();

    public void start(boolean win) throws Exception {
        /**
         * Méthode permettant de construire la vue puis l'afficher
         */
        public void start (boolean win) throws Exception {
            // Chargement de toutes les ressources nécessaires (polices et scène)
            Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
            Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
            Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
            Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/end_scene.fxml"));

            // On récupère les différents conteneurs principaux pour construire la fenêtre ensuite
            VBox root = (VBox) p;

            AnchorPane header = (AnchorPane) root.getChildren().get(0); // AnchorPane id "header"
            AnchorPane container = (AnchorPane) root.getChildren().get(1); // AnchorPane id "container"
            AnchorPane buttonsPane = (AnchorPane) container.getChildren().get(0); // AnchorPane id "button-pane"
            AnchorPane graphicPane = (AnchorPane) container.getChildren().get(1); // AnchorPane id "graphic-pane"

            //Évenemment permettant l'affichage d'un grapique pour un indicateur donné en paramètre
            EventHandler<ActionEvent> individualGraphicButton = event -> {
                graphicPane.getChildren().clear();

                String indicatorGroup = ((Button) event.getSource()).getText();

                Graphic g = new Graphic();
                LineChart<Integer, Long> iChart = g.getIndicatorGraphic(indicatorGroup.toLowerCase());

                iChart.setPrefWidth(graphicPane.getWidth() - 100);
                iChart.setPrefHeight(graphicPane.getHeight() - 50);

                iChart.setTitle("Graphique représentant l'évolution des indicateurs par semestre");
                graphicPane.getChildren().add(iChart);
                event.consume();
            };

            //Évemment permettant l'affichage de plusieurs graphiques
            EventHandler<ActionEvent> groupGraphicButton = event -> {
                graphicPane.getChildren().clear();

                String indicatorName = ((Button) event.getSource()).getText();

                Graphic g = new Graphic();
                LineChart<Integer, Long> iChart = g.getIndicatorsGraphic(indicatorName.toLowerCase());

                iChart.setPrefWidth(graphicPane.getWidth() - 100);
                iChart.setPrefHeight(graphicPane.getHeight() - 50);

                iChart.setTitle("Graphique représentant l'évolution d'un indicateur par semestre");
                graphicPane.getChildren().add(iChart);
                event.consume();
            };

            ArrayList<String> indicators = new ArrayList<>();
            for (Indicator indic : ec.getIndicators()) {
                String indicatorName = indic.get_name();
                indicators.add(indicatorName.substring(0, 1).toUpperCase() + indicatorName.substring(1));
            }

            for (Node n : buttonsPane.getChildren()) {
                if (n instanceof Button) {
                    Button b = (Button) n;
                    String bName = b.getText();
                    b.setTooltip(new InfoTooltip(bName));
                    if (indicators.contains(bName)) {
                        b.setOnAction(individualGraphicButton);
                    } else {
                        b.setOnAction(groupGraphicButton);
                    }
                }
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setFullScreen(true);
            stage.show();
        }
    }
}
