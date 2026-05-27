package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.commonbullet.BulletSPI;
import dk.sdu.commonscore.ScoreSPI;

import java.util.ServiceLoader;

public class PlayerControlSystem implements IEntityProcessingService {

    private boolean spacePressedLastFrame = false;
    private int frameCounter = 0;

    private final BulletSPI bulletSPI = ServiceLoader.load(BulletSPI.class)
            .findFirst()
            .orElse(null);

    private final ScoreSPI scoreService = ServiceLoader.load(ScoreSPI.class)
            .findFirst()
            .orElse(null);

    @Override
    public void process(GameData gameData, World world) {

        boolean spacePressedNow = gameData.getKeys().contains("SPACE");

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {

                MovingPart movingPart = entity.getPart(MovingPart.class);
                PositionPart positionPart = entity.getPart(PositionPart.class);

                movingPart.setLeft(gameData.getKeys().contains("LEFT"));
                movingPart.setRight(gameData.getKeys().contains("RIGHT"));
                movingPart.setUp(gameData.getKeys().contains("UP"));

                entity.processAllParts(gameData);

                if (spacePressedNow && !spacePressedLastFrame && bulletSPI != null) {
                    Entity bullet = bulletSPI.createBullet(entity, 1);
                    world.addEntity(bullet);
                }

                if (!entity.isHit()) {
                    frameCounter++;
                    if (frameCounter >= 60) {
                        frameCounter = 0;
                        if (scoreService != null) {
                            gameData.setScore(scoreService.addScore(1));
                        }
                    }
                }

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