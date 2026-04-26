package dk.sdu.enemy;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.commonenemy.Enemy;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Enemy) {

                MovingPart movingPart = entity.getPart(MovingPart.class);
                float rng = random.nextFloat();

                // Simpel AI — tilfældig bevægelse
                movingPart.setUp(rng > 0.1f && rng < 0.9f);
                movingPart.setLeft(rng < 0.2f);
                movingPart.setRight(rng > 0.8f);

                entity.processAllParts(gameData);

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