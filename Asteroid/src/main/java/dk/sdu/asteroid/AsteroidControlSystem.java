package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.commonasteroid.Asteroid;
import dk.sdu.commonasteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class AsteroidControlSystem implements IEntityProcessingService {

    private final IAsteroidSplitter splitter = ServiceLoader.load(IAsteroidSplitter.class)
            .findFirst()
            .orElse(null);

    @Override
    public void process(GameData gameData, World world) {

        List<Entity> toSplit = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid) {

                PositionPart pos = entity.getPart(PositionPart.class);
                pos.setRotation(pos.getRotation() + 0.5);

                entity.processAllParts(gameData);

                handleHit(entity, toSplit);
            }
        }

        if (splitter != null) {
            for (Entity asteroid : toSplit) {
                splitter.splitAsteroid(asteroid, world);
            }
        }
    }

    private void handleHit(Entity entity, List<Entity> toSplit) {
        if (entity.isHit()) {
            toSplit.add(entity);
        }
        entity.setIsHit(false);
    }
}
