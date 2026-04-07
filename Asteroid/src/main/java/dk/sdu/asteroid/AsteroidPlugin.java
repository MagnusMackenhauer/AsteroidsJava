package dk.sdu.asteroid;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 40; i++) {
            world.addEntity(createAsteroid(gameData));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (var entity : world.getEntities()) {
            if (entity instanceof Asteroid) {
                world.removeEntity(entity);
            }
        }
    }

    private Asteroid createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();

        asteroid.setRadius(5);

        asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(random.nextInt(gameData.getDisplayHeight()));

        asteroid.setRotation(random.nextInt(360));

        asteroid.setDx(random.nextDouble() * 2 - 1);
        asteroid.setDy(random.nextDouble() * 2 - 1);

        asteroid.setShape(new double[]{
                -12.0, -10.0,
                -6.0, -14.0,
                8.0, -12.0,
                14.0, -4.0,
                10.0, 10.0,
                0.0, 14.0,
                -10.0, 8.0,
                -14.0, -2.0
        });

        return asteroid;
    }
}