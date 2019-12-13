import javafx.application.Application;
import javafx.stage.Stage;
import view.IntroView;

public class GameLauncher extends Application {

    /**
     * Méthode permettant de lancer l'application JavaFX
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Root();
        IntroView.getInstance().start(stage);
    }

    /**
     * Programme principal initiant le lancement de l'application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
