package dk.sdu.cbse.main;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ServiceLoader;

public class Game {

    // Fælles game data, fx skærmstørrelse og input
    private final GameData gameData = new GameData();

    // Verden med alle entities
    private final World world = new World();

    // JavaFX pane hvor spillet tegnes
    private final Pane gameWindow = new Pane();

    public void start(Stage window) {

        // Sætter størrelsen på vinduet
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        // Opretter JavaFX scene
        Scene scene = new Scene(gameWindow, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        // Når en tast trykkes ned, gemmer vi den i GameData
        scene.setOnKeyPressed(e -> gameData.getKeys().add(e.getCode().toString()));

        // Når en tast slippes, fjerner vi den fra GameData
        scene.setOnKeyReleased(e -> gameData.getKeys().remove(e.getCode().toString()));

        // Loade alle plugins som implementerer IGamePluginService
        // Fx PlayerPlugin
        for (IGamePluginService plugin : ServiceLoader.load(IGamePluginService.class)) {
            plugin.start(gameData, world);
        }

        // Starter game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Opdaterer logik
                update();

                // Tegner entities
                draw();
            }
        }.start();

        // Sætter vinduets titel
        window.setTitle("ASTEROIDS");

        // Sætter scene på vinduet
        window.setScene(scene);

        // Gør vinduet ikke-resizable
        window.setResizable(false);

        // Viser vinduet
        window.show();
    }

    private void update() {

        // Loade alle processeringssystemer som implementerer IEntityProcessingService
        // Fx PlayerControlSystem
        for (IEntityProcessingService processor : ServiceLoader.load(IEntityProcessingService.class)) {
            processor.process(gameData, world);
        }
    }

    private void draw() {

        // Fjerner alt der er tegnet i sidste frame
        gameWindow.getChildren().clear();

        // Tegner alle entities i verden
        for (Entity entity : world.getEntities()) {

            // Opretter en polygon ud fra entityens shape
            Polygon polygon = new Polygon(entity.getShape());

            // Flytter polygonen til entityens position
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());

            // Roterer polygonen så den følger entityens rotation
            polygon.setRotate(entity.getRotation());

            // Tilføjer polygonen til vinduet
            gameWindow.getChildren().add(polygon);
        }
    }
}