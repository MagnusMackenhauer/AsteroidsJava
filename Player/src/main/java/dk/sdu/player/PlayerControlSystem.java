package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.commonbullet.BulletSPI;

import java.util.ServiceLoader;

public class PlayerControlSystem implements IEntityProcessingService {

    private boolean spacePressedLastFrame = false;

    private final BulletSPI bulletSPI = ServiceLoader.load(BulletSPI.class)
            .findFirst()
            .orElse(null);

    @Override
    public void process(GameData gameData, World world) {

        boolean spacePressedNow = gameData.getKeys().contains("SPACE");

        for (Entity entity : world.getEntities()) {

            if (entity instanceof Player) {

                MovingPart movingPart = entity.getPart(MovingPart.class);
                PositionPart positionPart = entity.getPart(PositionPart.class);

                // Sæt input-flags på MovingPart
                movingPart.setLeft(gameData.getKeys().contains("LEFT"));
                movingPart.setRight(gameData.getKeys().contains("RIGHT"));
                movingPart.setUp(gameData.getKeys().contains("UP"));

                // Lad parts håndtere bevægelse og wraparound
                entity.processAllParts(gameData);

                // Skydning
                if (spacePressedNow && !spacePressedLastFrame && bulletSPI != null) {
                    Entity bullet = bulletSPI.createBullet(entity);
                    world.addEntity(bullet);
                }

                // Hit-håndtering
                handleHit(entity, world);
            }
        }

        spacePressedLastFrame = spacePressedNow;
    }

    private void handleHit(Entity entity, World world) {
        if (entity.isHit()) {
            world.removeEntity(entity);
        }
        entity.setIsHit(false);
    }
}
