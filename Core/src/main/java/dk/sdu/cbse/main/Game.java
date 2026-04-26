package dk.sdu.cbse.main;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.List;

public class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();

    private final List<IGamePluginService> plugins;
    private final List<IEntityProcessingService> processors;

    // Spring injicerer via ModuleConfig.game()
    public Game(List<IGamePluginService> plugins, List<IEntityProcessingService> processors) {
        this.plugins = plugins;
        this.processors = processors;
    }

    public void start(Stage window) {

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Scene scene = new Scene(gameWindow, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        scene.setOnKeyPressed(e -> gameData.getKeys().add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> gameData.getKeys().remove(e.getCode().toString()));

        for (IGamePluginService plugin : plugins) {
            plugin.start(gameData, world);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
            }
        }.start();

        window.setTitle("ASTEROIDS");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    private void update() {
        for (IEntityProcessingService processor : processors) {
            processor.process(gameData, world);
        }
    }

    private void draw() {
        gameWindow.getChildren().removeIf(node -> node instanceof Polygon);

        for (Entity entity : world.getEntities()) {
            PositionPart pos = entity.getPart(PositionPart.class);
            if (pos == null) continue;

            Polygon polygon = new Polygon(entity.getShape());
            polygon.setTranslateX(pos.getX());
            polygon.setTranslateY(pos.getY());
            polygon.setRotate(pos.getRotation());
            gameWindow.getChildren().add(polygon);
        }
    }
}