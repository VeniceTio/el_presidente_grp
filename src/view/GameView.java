package view;

import controller.ElementControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Indicator;
import model.Lever;

import java.awt.*;

public class GameView extends Application {
    public void start(Stage stage) throws Exception {
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

        // Indicateurs
        for(Indicator ind : ElementControl.getInstance().getIndicators()) {
            String indicatorName = ind.get_name();
            Text itxt = new IndicatorText(indicatorName).getText();
            if(indicatorName == "argent disponible") {
                availableMoneyPane.getChildren().add(itxt);
                itxt.setX(70);
                itxt.setY(70);
            } else {
                itxt.setY(offsetY);
                offsetY += 20;
                indicatorsPane.getChildren().add(itxt);
            }
        }

        // Leviers
        offsetY = 0;
        for(Lever lev : ElementControl.getInstance().getLevers()) {
            Pane lcp = new LeverController(lev.get_name()).getPane();
            lcp.setTranslateX(offsetX);
            lcp.setTranslateY(offsetY);
            offsetX += 250;
            leversPane.getChildren().add(lcp);

            if(offsetX > 2*250) {
                offsetX = 0;
                offsetY += 75;
            }
        }

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.setFullScreen(true);
        stage.show();
    }

    public static void initialize() {
        launch();
    }

}
