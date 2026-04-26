package dk.sdu.asteroid;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.commonasteroid.Asteroid;

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

        asteroid.add(new PositionPart(
                random.nextInt(gameData.getDisplayWidth()),
                random.nextInt(gameData.getDisplayHeight()),
                random.nextInt(360)
        ));

        MovingPart movingPart = new MovingPart(1.0, 0, 2, 0.5);
        movingPart.setDx(random.nextDouble() * 2 - 1);
        movingPart.setDy(random.nextDouble() * 2 - 1);
        asteroid.add(movingPart);

        asteroid.setCollisionGroup(2);


        return asteroid;
    }
}
