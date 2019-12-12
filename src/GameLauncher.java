import javafx.application.Application;
import javafx.stage.Stage;
import view.EndView;
import view.IntroView;

public class GameLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new Root();
        IntroView.getInstance().start(stage);
        EndView.getInstance().start();
        // GameView.getInstance().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
