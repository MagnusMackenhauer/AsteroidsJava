package dk.sdu.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }

                if (collides(entity1, entity2) && entity1.getCollisionGroup() != entity2.getCollisionGroup()) {
                    entity1.setIsHit(true);
                    entity2.setIsHit(true);
                }
            }
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        PositionPart pos1 = entity1.getPart(PositionPart.class);
        PositionPart pos2 = entity2.getPart(PositionPart.class);

        if (pos1 == null || pos2 == null) return false;

        float dx = (float) pos1.getX() - (float) pos2.getX();
        float dy = (float) pos1.getY() - (float) pos2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
