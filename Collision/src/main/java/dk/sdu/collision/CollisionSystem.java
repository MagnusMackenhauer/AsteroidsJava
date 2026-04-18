package dk.sdu.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

public class CollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }

                if (collides(entity1, entity2) && entity1.getClass() != entity2.getClass()) {
                    entity1.setIsHit(true);
                    entity2.setIsHit(true);
                }
            }
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}