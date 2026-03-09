package dk.sdu.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // JavaFX entry point
    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) {

        // Opretter spillet og starter det
        Game game = new Game();
        game.start(stage);
    }
}