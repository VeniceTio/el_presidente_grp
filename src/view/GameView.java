package view;

import controller.ElementControl;
import javafx.application.Application;
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

public class GameView extends Application {
    public void start(Stage stage) throws Exception {
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
                Pane lcp = new LeverController(l.get_name()).getPane();
                lcp.setTranslateX(offsetX);
                lcp.setTranslateY(offsetY);
                offsetX += 250;
                leversPane.getChildren().add(lcp);

                if(offsetX > 3*250) {
                    offsetX = 0;
                    offsetY += 75;
                }
            }
            offsetY += 150;
            offsetX = 0;
        }

        // Indicateurs
        offsetY = 0;
        for(Indicator ind : ElementControl.getInstance().getIndicators()) {
            String indicatorName = ind.get_name();
            Text itxt = new IndicatorText(indicatorName).getText();
            offsetY += 20;
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
