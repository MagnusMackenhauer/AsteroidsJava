package dk.sdu.enemy;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.commonbullet.BulletSPI;
import dk.sdu.commonenemy.Enemy;

import java.util.Random;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService {

    private final Random random = new Random();
    private int fireCounter = 0;

    private final BulletSPI bulletSPI = ServiceLoader.load(BulletSPI.class)
            .findFirst()
            .orElse(null);

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Enemy) {

                MovingPart movingPart = entity.getPart(MovingPart.class);
                float rng = random.nextFloat();

                movingPart.setUp(rng > 0.1f && rng < 0.9f);
                movingPart.setLeft(rng < 0.2f);
                movingPart.setRight(rng > 0.8f);

                entity.processAllParts(gameData);

                fireCounter++;
                if (fireCounter >= 120 && bulletSPI != null) {
                    fireCounter = 0;
                    Entity bullet = bulletSPI.createBullet(entity, 3); // gruppe 3 rammer player
                    world.addEntity(bullet);
                }

                handleHit(entity, world);
            }
        }
    }

    private void handleHit(Entity entity, World world) {
        if (entity.isHit()) {
            world.removeEntity(entity);
        }
        entity.setIsHit(false);
    }
}