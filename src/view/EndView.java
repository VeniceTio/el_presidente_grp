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

public class EndView {
    private static EndView _instance = null;

    public static EndView getInstance() {
        if(_instance == null) {
           _instance = new EndView();
        }
        return _instance;
    }

    ElementControl ec = ElementControl.getInstance();

    public void start(boolean win) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/end_scene.fxml"));
        VBox root = (VBox) p;

        AnchorPane header = (AnchorPane) root.getChildren().get(0); // AnchorPane id "header"
        AnchorPane container = (AnchorPane) root.getChildren().get(1); // AnchorPane id "container"
        AnchorPane buttonsPane = (AnchorPane) container.getChildren().get(0); // AnchorPane id "button-pane"
        AnchorPane graphicPane = (AnchorPane) container.getChildren().get(1); // AnchorPane id "graphic-pane"

        EventHandler<ActionEvent> individualGraphicButton = event -> {
            graphicPane.getChildren().clear();

            String indicatorGroup = ((Button) event.getSource()).getText();

            LineChart<Integer, Long> iChart = getIndicatorGraphic(indicatorGroup.toLowerCase());

            iChart.setPrefWidth(graphicPane.getWidth() - 100);
            iChart.setPrefHeight(graphicPane.getHeight() - 50);

            iChart.setTitle("Graphique représentant l'évolution des indicateurs par semestre");

            graphicPane.getChildren().add(iChart);

            event.consume();
        };

        EventHandler<ActionEvent> groupGraphicButton = event -> {
            graphicPane.getChildren().clear();

            String indicatorName = ((Button) event.getSource()).getText();

            LineChart<Integer, Long> iChart = getIndicatorsGraphic(indicatorName.toLowerCase());

            iChart.setPrefWidth(graphicPane.getWidth() - 100);
            iChart.setPrefHeight(graphicPane.getHeight() - 50);

            iChart.setTitle("Graphique représentant l'évolution d'un indicateur par semestre");

            graphicPane.getChildren().add(iChart);

            event.consume();
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
                    b.setOnAction(groupGraphicButton);
                }
            }
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setFullScreen(true);
        stage.show();
    }

    private LineChart<Integer, Long> getIndicatorGraphic(String name) {
        ArrayList<Indicator> indicatorList = ec.getIndicators();
        int indicatorIndex = -1;

        for (int i = 0; i < indicatorList.size(); i++) {
            Indicator indic = indicatorList.get(i);
            if (indic.get_name().equals(name)) {
                indicatorIndex = i;
            }
        }

        ArrayList<Long> indicatorHistory = indicatorList.get(indicatorIndex).get_history();

        Long maxValue =  indicatorHistory.get(0);
        for (Long tempValue : indicatorHistory) {
            maxValue = Math.max(tempValue, maxValue);
        }

        int tickValue = Math.toIntExact(maxValue / 10);
        int upperBoundY = Math.toIntExact(maxValue + tickValue);

        String indicatorType = "";
        if(indicatorList.get(indicatorIndex).getType().equals(IndicatorType.PERCENTAGE)) {
            indicatorType = "(%)";
            upperBoundY = 100;
            tickValue = 5;
        }

        NumberAxis xAxis = new NumberAxis(0, indicatorHistory.size() -1, 1);
        xAxis.setLabel("Semestre");

        System.out.println(indicatorHistory.size());

        NumberAxis yAxis = new NumberAxis(0, upperBoundY, tickValue);
        yAxis.setLabel("Valeur de l'indicateur" + indicatorType);

        LineChart<Integer, Long> indicatorChart = new LineChart(xAxis, yAxis);
        XYChart.Series<Integer, Long> series = new XYChart.Series<>();

        String displayName = name.substring(0, 1).toUpperCase() + name.substring(1);
        series.setName(displayName);

        for(int i = 0; i < indicatorHistory.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, indicatorHistory.get(i)));
        }

        indicatorChart.getData().add(series);
        return indicatorChart;
    }

    private LineChart<Integer, Long> getIndicatorsGraphic(String name) {
        ArrayList<Indicator> indicatorList = ec.getIndicators();
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < indicatorList.size(); i++) {
            Indicator indic = indicatorList.get(i);
            String cutName = name.substring(0, name.length() - 1);
            String tempName = indic.get_name().substring(0, cutName.length());

            if(cutName.equals(tempName))
                indexes.add(i);
        }

        ArrayList<Long> indicatorHistory1 = indicatorList.get(indexes.get(0)).get_history();
        ArrayList<Long> indicatorHistory2 = indicatorList.get(indexes.get(1)).get_history();

        Long maxValue = indicatorHistory1.get(0);
        for(Long l1 : indicatorHistory1) {
            for(Long l2 : indicatorHistory2) {
                maxValue = Math.max(l1, l2);
            }
        }

        int tickValue = Math.toIntExact(maxValue / 10);
        int upperBoundY = Math.toIntExact(maxValue + tickValue);

        String indicatorType = "";

        IndicatorType iType1 = indicatorList.get(indexes.get(0)).getType();
        IndicatorType iType2 = indicatorList.get(indexes.get(1)).getType();

        if(iType1.equals(IndicatorType.PERCENTAGE) && iType2.equals(IndicatorType.PERCENTAGE)) {
            indicatorType = "(%)";
            upperBoundY = 100;
            tickValue = 5;
        }

        int upperBoundX = Math.max(indicatorHistory1.size(), indicatorHistory2.size());
        NumberAxis xAxis = new NumberAxis(0, upperBoundX - 1, 1);
        xAxis.setLabel("Semestre");

        NumberAxis yAxis = new NumberAxis(0, upperBoundY, tickValue);
        yAxis.setLabel("Valeur de l'indicateur" + indicatorType);

        LineChart<Integer, Long> indicatorsChart = new LineChart(xAxis, yAxis);

        XYChart.Series<Integer, Long> series1 = new XYChart.Series<>();
        XYChart.Series<Integer, Long> series2 = new XYChart.Series<>();

        String tempName1 = indicatorList.get(indexes.get(0)).get_name();
        String tempName2 = indicatorList.get(indexes.get(1)).get_name();

        String displayName1 = tempName1.substring(0, 1).toUpperCase() + tempName1.substring(1);
        String displayName2 = tempName1.substring(0, 1).toUpperCase() + tempName2.substring(1);

        series1.setName(displayName1);
        series2.setName(displayName2);

        int size = indicatorHistory1.size();

        if(indicatorHistory2.size() < size)
            size = indicatorHistory2.size();

        for(int index = 0; index < size; index++) {
            series1.getData().add(new XYChart.Data<>(index, indicatorHistory1.get(index)));
            series2.getData().add(new XYChart.Data<>(index, indicatorHistory2.get(index)));
        }

        indicatorsChart.getData().add(series1);
        indicatorsChart.getData().add(series2);

        return indicatorsChart;
    }
}
