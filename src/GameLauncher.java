import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;
import view.IntroView;

public class GameLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Root();
        IntroView.getInstance().start(stage);
        // GameView.getInstance().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
