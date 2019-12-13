package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OptionsView {
    /**
     * Instance de la vue
     */
    private static OptionsView _instance = null;

    /**
     * Méthode renvoyant l'instance de la vue
     * @return L'instance de la vue
     */
    public static OptionsView getInstance() {
        if(_instance == null)
            _instance = new OptionsView();

        return _instance;
    }

    /**
     * Méthode permettant de construire puis afficher la vue
     */
    public void start() throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("../resources/fxml/options_scene.fxml"));

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("El Presidente");

        stage.setScene(new Scene(p));
        stage.show();
    }
}