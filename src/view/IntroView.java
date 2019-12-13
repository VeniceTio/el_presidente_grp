package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class IntroView{
    /**
     * Instance de la vue
     */
    private static IntroView _instance = null;

    /**
     * Méthode renvoyant l'instance de la vue
     * @return L'instance de la vue
     */
    public static IntroView getInstance() {
        if(_instance == null)
            _instance = new IntroView();

        return _instance;
    }

    /**
     * Méthode permettant de construire puis afficher la vue
     * @param stage Fenêtre initiale générée par JavaFX
     */
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/intro_scene.fxml"));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.show();
    }
}
