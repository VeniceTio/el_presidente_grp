import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;

public class GameLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Root();
        GameView.getInstance().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
