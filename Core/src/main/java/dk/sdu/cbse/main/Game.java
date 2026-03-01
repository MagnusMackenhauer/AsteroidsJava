package dk.sdu.cbse.main;

import dk.sdu.cbse.common.GameData;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {

    private final GameData gameData = new GameData();
    private final Pane gameWindow = new Pane();

    public void start(Stage window) {
        // her bestemmer du størrelsen (MVP)
        gameData.setDisplayWidth(200);
        gameData.setDisplayHeight(200);

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(new Text(10, 20, "AsteroidsJava MVP"));

        Scene scene = new Scene(gameWindow, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        window.setTitle("ASTEROIDS");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}