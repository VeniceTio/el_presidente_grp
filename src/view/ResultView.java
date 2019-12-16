package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultView {
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

    public void start(boolean win) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Cocogoose.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("../resources/fonts/Roboto-Bold.ttf"), 16);
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/result_scene.fxml"));

        VBox root = (VBox) p;
        System.out.println(root.getChildren());
        AnchorPane container = (AnchorPane) root.getChildren().get(0);

        Text txt = (Text) container.getChildren().get(0);
        txt.setText("Vous avez gagné, félicitations");

        if(!win) {
            txt.setText("Vous avez perdu, réssayez");
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setFullScreen(true);
        stage.show();
    }
}
