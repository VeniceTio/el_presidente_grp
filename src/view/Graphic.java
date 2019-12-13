package view;

import controller.ElementControl;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Indicator;
import model.IndicatorType;

import java.util.ArrayList;

public class Graphic {
    ElementControl ec = ElementControl.getInstance();

    /**
     * Méthode construisant un graphique de type "LineChart"
     * d'un graphique puis le renvoie
     * @param name l'indicateur sur lequel on créer le graphe
     * @return le graphe de l'indicateur
     */
    public LineChart<Integer, Long> getIndicatorGraphic(String name) {
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

    /**
     * Méthode construisant une graphique de plusieurs graphiques
     * de type "LineChart" puis le renvoie
     * @param name le nom de la famille de l'indicateur
     * @return le graphe des indicateurs
     */
    public LineChart<Integer, Long> getIndicatorsGraphic(String name) {
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
