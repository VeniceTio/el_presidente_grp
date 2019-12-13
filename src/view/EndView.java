package view;

import controller.ElementControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Indicator;
import model.IndicatorType;

import java.util.ArrayList;
import java.util.Arrays;

public class EndView {
    /**
     * Instance de la vue
     */
    private static EndView _instance = null;

    /**
     * Méthode renvoyant l'instance de la vue
     * @return L'instance de la vue
     */
    public static EndView getInstance() {
        if(_instance == null) {
           _instance = new EndView();
        }
        return _instance;
    }

    /**
     * Méthode permettant de construire puis afficher la vue
     */
    @SuppressWarnings("unchecked")
    public void start() throws Exception {
        ElementControl ec = ElementControl.getInstance();

        // Chargement des ressources nécessaires
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/end_scene.fxml"));

        // Construction de la fenêtre
        VBox root = (VBox) p;
        AnchorPane header = (AnchorPane) root.getChildren().get(0); // AnchorPane id "header"
        AnchorPane container = (AnchorPane) root.getChildren().get(1); // AnchorPane id "container"
        AnchorPane buttonsPane = (AnchorPane) container.getChildren().get(0); // AnchorPane id "button-pane"
        AnchorPane graphicPane = (AnchorPane) container.getChildren().get(1); // AnchorPane id "graphic-pane"

        EventHandler<ActionEvent> individualGraphicButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                graphicPane.getChildren().clear();

                String indicatorGroup = ((Button) event.getSource()).getText();

                LineChart iChart = getIndicatorGraphic(indicatorGroup.toLowerCase());

                iChart.setPrefWidth(graphicPane.getWidth() - 100);
                iChart.setPrefHeight(graphicPane.getHeight() - 50);

                iChart.setTitle("Graphique représentant l'évolution des indicateurs par semestre");

                graphicPane.getChildren().add(iChart);

                event.consume();
            }
        };

        EventHandler<ActionEvent> groupGrpahicButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                graphicPane.getChildren().clear();

                String indicatorName = ((Button) event.getSource()).getText();

                LineChart iChart = getGroupGraphic(indicatorName.toLowerCase());

                iChart.setPrefWidth(graphicPane.getWidth() - 100);
                iChart.setPrefHeight(graphicPane.getHeight() - 50);

                iChart.setTitle("Graphique représentant l'évolution d'un indicateur par semestre");

                graphicPane.getChildren().add(iChart);

                event.consume();
            }
        };


        String[] indicators = {"Satisfaction étudiante",
                "Nombre d'étudiants", "Taux de réussite",
                "Recherche fondamentale", "Nombre d'articles publiés",
                "Réputation de la recherche", "Qualité des formations",
                "État des batiments", "Satisfaction professeurs",
                "Nombre de professeurs", "Taux d'insertion pro.",
                "Recherche appliquée", "Nombre de prix nobel",
                "Réputation des formations", "Argent disponible"};
        ArrayList<String> indicatorButton = new ArrayList<>(Arrays.asList(indicators));

        for(Node n : buttonsPane.getChildren()) {
            if(n instanceof Button) {
                Button b = (Button) n;
                if(indicatorButton.contains(b.getText())) {
                    b.setOnAction(individualGraphicButton);
                }
                else {
                    b.setOnAction(groupGrpahicButton);
                }
            }
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setFullScreen(true);
        stage.show();
    }

    public LineChart getIndicatorGraphic(String name) {
        ElementControl ec = ElementControl.getInstance();
        ArrayList<Indicator> indicatorList = ec.getIndicators();
        int indicatorIndex = -1;

        for (int i = 0; i < indicatorList.size(); i++) {
            Indicator indic = indicatorList.get(i);
            if (indic.get_name().equals(name)) {
                indicatorIndex = i;
            }
        }

        ArrayList<Long> indicatorHistory = indicatorList.get(indicatorIndex).get_history();

        NumberAxis xAxis = new NumberAxis(0, indicatorHistory.size() -1, 1);
        xAxis.setLabel("Semestre");

        Long maxValue =  indicatorHistory.get(0);

        for (Long tempValue : indicatorHistory) {
            if (maxValue < tempValue)
                maxValue = tempValue;
        }

        int tickNum = Math.toIntExact(maxValue / 10);

        int lowerBoundY = 0;
        int upperBoundY = (int) (maxValue + tickNum);
        int tick = tickNum;

        String indicatorType = "";

        if(indicatorList.get(indicatorIndex).getType() == IndicatorType.PERCENTAGE) {
            indicatorType = "(%)";
            upperBoundY = 100;
            tick = 10;
        }

        NumberAxis yAxis = new NumberAxis(lowerBoundY, upperBoundY, tick);

        yAxis.setLabel("Valeur de l'indicateur" + indicatorType);

        LineChart indicatorChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();

        String displayName = name.substring(0, 1).toUpperCase() + name.substring(1);
        series.setName(displayName + " au cours des semestre");

        for(int i = 0; i < indicatorHistory.size(); i++) {
            series.getData().add(new XYChart.Data(i, indicatorHistory.get(i)));
        }

        indicatorChart.getData().add(series);

        return indicatorChart;
    }

    public LineChart getGroupGraphic(String name) {
        ElementControl ec = ElementControl.getInstance();
        ArrayList<Indicator> indicatorList = ec.getIndicators();
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < indicatorList.size(); i++) {
            Indicator indic = indicatorList.get(i);
            String cutName = name.substring(0, name.length() - 1);

            System.out.println(indic.get_name());
            System.out.println(cutName);

            if(cutName.equals(indic.get_name().substring(0, cutName.length()))) {
                indexes.add(i);
                System.out.println("oui");
            }
            else {
                System.out.println("non");
            }
        }

        ArrayList<Long> indicatorHistory1 = indicatorList.get(indexes.get(0)).get_history();
        ArrayList<Long> indicatorHistory2 = indicatorList.get(indexes.get(1)).get_history();

        Integer upperBound = indicatorHistory1.size();

        if(indicatorHistory2.size() > upperBound)
            upperBound = indicatorHistory2.size();

        NumberAxis xAxis = new NumberAxis(0, upperBound - 1, 1);
        xAxis.setLabel("Semestre");

        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        yAxis.setLabel("Valeur de l'indicateur en (%)");

        LineChart indicatorsChart = new LineChart(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        String tempName1= indicatorList.get(indexes.get(0)).get_name();
        String tempName2 = indicatorList.get(indexes.get(1)).get_name();

        String displayName1 = tempName1.substring(0, 1).toUpperCase() + tempName1.substring(1);
        String displayName2 = tempName1.substring(0, 1).toUpperCase() + tempName2.substring(1);

        series1.setName(displayName1);
        series2.setName(displayName2);

        Integer size = indicatorHistory1.size();

        if(indicatorHistory2.size() < size)
            size = indicatorHistory2.size();

        for(int y = 0; y < size; y++) {
            series1.getData().add(new XYChart.Data(y, indicatorHistory1.get(y)));
            series2.getData().add(new XYChart.Data(y, indicatorHistory2.get(y)));
        }

        indicatorsChart.getData().addAll(series1, series2);

        return indicatorsChart;
    }
}
