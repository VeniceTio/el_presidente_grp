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

public class GameView {
    private static GameView _instance = null;

    public static GameView getInstance() {
        if(_instance == null)
            _instance = new GameView();

        return _instance;
    }
    public void start() throws Exception {
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/game_scene.fxml"));

        VBox root = (VBox) p;
        AnchorPane header = (AnchorPane) root.getChildren().get(0); // AnchorPane id "header"
        AnchorPane availableMoneyPane = (AnchorPane) header.getChildren().get(4); // AnchorPane id "available-money-pane"
        AnchorPane container = (AnchorPane) root.getChildren().get(1); // AnchorPane id "container"
        AnchorPane indicatorsPane = (AnchorPane) container.getChildren().get(0); // AnchorPane id "indicators"
        AnchorPane leversPane = (AnchorPane) container.getChildren().get(1); // AnchorPane id "levers"

        // Génération dynamique des leviers et indicateurs
        double offsetX = 0;
        double offsetY = 0;

        ElementControl ec = ElementControl.getInstance();
        for(LeverFamily lf : ec.getFamilyLevers()) {
            Text famNameText = new Text(lf.getName().toUpperCase());
            DropShadow ds = new DropShadow();
            ds.setRadius(0);
            ds.setOffsetX(-2.0f);
            ds.setColor(Color.rgb(231, 63, 8));
            famNameText.setEffect(ds);
            famNameText.setFont(new Font("Cocogoose", 32));
            famNameText.setFill(Color.WHITE);
            famNameText.setY(offsetY);
            leversPane.getChildren().add(famNameText);
            offsetY += 20;
            for(Lever l : lf.getLevers()) {
                if(l.get_name() != "rValorisation") {
                    Pane lcp = new LeverController(l.get_name()).getPane();
                    lcp.setTranslateX(offsetX);
                    lcp.setTranslateY(offsetY);
                    offsetX += 250;
                    leversPane.getChildren().add(lcp);

                    if (offsetX > 3 * 250) {
                        offsetX = 0;
                        offsetY += 75;
                    }
                }
            }
            offsetY += 150;
            offsetX = 0;
        }

        // Indicateurs
        offsetX = 0;
        offsetY = 0;
        String[] hiddenIndicators = {"revenus des inscriptions", "valorisation batiment", "valorisation bien", "subventions de l'état"};
        ArrayList<String> hiddenIndicatosrAL = new ArrayList<String>(Arrays.asList(hiddenIndicators));
        for(Indicator ind : ElementControl.getInstance().getIndicators()) {
            if(!hiddenIndicatosrAL.contains(ind.get_name())) {
                String indicatorName = ind.get_name();
                Pane indicatorText = new IndicatorText(indicatorName).getPane();

                if(indicatorName == "argent disponible") {
                    indicatorText.setTranslateX(70);
                    indicatorText.setTranslateY(16);
                    availableMoneyPane.getChildren().add(indicatorText);
                } else {
                    indicatorText.setTranslateX(offsetX);
                    indicatorText.setTranslateY(offsetY);
                    indicatorsPane.getChildren().add(indicatorText);
                    offsetX += 280;

                    if(offsetX > 2*350) {
                        offsetX = 0;
                        offsetY += 100;
                    }
                }
            }
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.setFullScreen(true);
        stage.show();
    }
}
