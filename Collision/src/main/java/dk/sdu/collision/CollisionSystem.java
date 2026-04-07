package dk.sdu.collision;

import dk.sdu.cbse.common.CollisionEvent;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.ICollidable;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

import java.util.List;

public class CollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        world.clearCollisionEvents();
        List<Entity> entities = world.getEntities();

        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);

            if (!(entity1 instanceof ICollidable)) {
                continue;
            }

            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);

                if (!(entity2 instanceof ICollidable)) {
                    continue;
                }

                if (collides(entity1, entity2)) {
                    world.addCollisionEvent(new CollisionEvent(entity1, entity2));
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