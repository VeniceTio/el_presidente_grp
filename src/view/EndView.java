package view;

import controller.ElementControl;
import javafx.application.Application;
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

    public void start() throws Exception {
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/end_scene.fxml"));
        VBox root = (VBox) p;

        AnchorPane header = (AnchorPane) root.getChildren().get(0); // AnchorPane id "header"
        AnchorPane container = (AnchorPane) root.getChildren().get(1); // AnchorPane id "container"
        AnchorPane buttonsPane = (AnchorPane) container.getChildren().get(0); // Anchor id "button-pane"
        AnchorPane graphicPane = (AnchorPane) container.getChildren().get(1); // Anchor id "graphic-pane"

        EventHandler<ActionEvent> graphicButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String indicatorName = ((Button) event.getSource()).getText();

                LineChart iChart = getIndicatorGraphic(indicatorName);
                graphicPane.getChildren().add(iChart);

                event.consume();
            }
        };

        for(Node n : buttonsPane.getChildren()) {
            Button b = (Button) n;

            b.setOnAction(graphicButtonHandler);
        }
        Stage stage = new Stage();

        stage.setScene(new Scene(p));
        stage.setMaximized(true);
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
            System.out.println(indicatorIndex);
        }

        ArrayList<Long> indicatorHistory = (ArrayList<Long>) indicatorList.get(indicatorIndex).get_history();

        NumberAxis xAxis = new NumberAxis(0, indicatorHistory.size() -1, 1);
        xAxis.setLabel("Semestre");

        Long maxValue =  indicatorHistory.get(0);

        for (Long tempValue : indicatorHistory) {
            if (maxValue < tempValue)
                maxValue = tempValue;
        }

        int tickNum = Math.toIntExact(maxValue / 10);

        NumberAxis yAxis = new NumberAxis(0, maxValue, tickNum);
        yAxis.setLabel("Valeur de l'indicateur");

        LineChart indicatorChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Évolution de l'indicateur sur chaque semestre");

        for(int i = 0; i < indicatorHistory.size(); i++) {
            series.getData().add(new XYChart.Data(i, indicatorHistory.get(i)));
        }

        indicatorChart.getData().add(series);

        return indicatorChart;
    }
}
