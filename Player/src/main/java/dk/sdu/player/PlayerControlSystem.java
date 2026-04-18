package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.commonbullet.BulletSPI;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

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

                if (gameData.getKeys().contains("LEFT"))
                    entity.setRotation(entity.getRotation() - 3);
                if (gameData.getKeys().contains("RIGHT"))
                    entity.setRotation(entity.getRotation() + 3);

                if (gameData.getKeys().contains("UP")) {
                    double radians = Math.toRadians(entity.getRotation() - 90);
                    entity.setDx(entity.getDx() + Math.cos(radians) * 0.1);
                    entity.setDy(entity.getDy() + Math.sin(radians) * 0.1);
                }

                entity.setX(entity.getX() + entity.getDx());
                entity.setY(entity.getY() + entity.getDy());

                entity.setDx(entity.getDx() * 0.98);
                entity.setDy(entity.getDy() * 0.98);

                if (entity.getX() < 0) entity.setX(gameData.getDisplayWidth());
                if (entity.getX() > gameData.getDisplayWidth()) entity.setX(0);
                if (entity.getY() < 0) entity.setY(gameData.getDisplayHeight());
                if (entity.getY() > gameData.getDisplayHeight()) entity.setY(0);

                if (spacePressedNow && !spacePressedLastFrame && bulletSPI != null) {
                    Entity bullet = bulletSPI.createBullet(entity);
                    world.addEntity(bullet);
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