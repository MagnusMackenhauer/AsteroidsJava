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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ServiceLoader;
/*
public class Game {

    // Fælles game data, fx skærmstørrelse og input
    private final GameData gameData = new GameData();

    // Verden med alle entities
    private final World world = new World();

    // JavaFX pane hvor spillet tegnes
    private final Pane gameWindow = new Pane();

    private final java.util.List<IEntityProcessingService> processors = new java.util.ArrayList<>();


    public void start(Stage window) {

        // Sætter størrelsen på vinduet
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        // Score-tekst
        Text scoreText = new Text(10, 20, "Score: 0");
        gameWindow.getChildren().add(scoreText);

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

                // Opdaterer score-teksten
                scoreText.setText("Score: " + gameData.getScore());
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

        // ✅ ÆNDRET: Bruger den cachede liste i stedet for ServiceLoader.load() hver frame
        for (IEntityProcessingService processor : processors) {
            processor.process(gameData, world);
        }
    }

    private void draw() {

        // Fjerner kun polygons fra sidste frame
        // Så score-teksten bliver stående
        gameWindow.getChildren().removeIf(node -> node instanceof Polygon);

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
*/


public class Game {

    // Fælles game data, fx skærmstørrelse og input
    private final GameData gameData = new GameData();

    // Verden med alle entities
    private final World world = new World();

    // JavaFX pane hvor spillet tegnes
    private final Pane gameWindow = new Pane();

    // ✅ ÆNDRET: Cache af processorer så samme instanser genbruges på tværs af frames
    private final java.util.List<IEntityProcessingService> processors = new java.util.ArrayList<>();

    public void start(Stage window) {

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Text scoreText = new Text(10, 20, "Score: 0");
        gameWindow.getChildren().add(scoreText);

        Scene scene = new Scene(gameWindow, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        scene.setOnKeyPressed(e -> gameData.getKeys().add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> gameData.getKeys().remove(e.getCode().toString()));

        for (IGamePluginService plugin : ServiceLoader.load(IGamePluginService.class)) {
            plugin.start(gameData, world);
        }

        // ✅ ÆNDRET: Processorer indlæses én gang her i stedet for hver frame
        for (IEntityProcessingService processor : ServiceLoader.load(IEntityProcessingService.class)) {
            processors.add(processor);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                scoreText.setText("Score: " + gameData.getScore());
            }
        }.start();

        window.setTitle("ASTEROIDS");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    private void update() {

        // ✅ ÆNDRET: Bruger den cachede liste i stedet for ServiceLoader.load() hver frame
        for (IEntityProcessingService processor : processors) {
            processor.process(gameData, world);
        }
    }

    private void draw() {

        gameWindow.getChildren().removeIf(node -> node instanceof Polygon);

        for (Entity entity : world.getEntities()) {

            Polygon polygon = new Polygon(entity.getShape());
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            gameWindow.getChildren().add(polygon);
        }
    }
}