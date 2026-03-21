package dk.sdu.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.CollisionEvent;
import dk.sdu.cbse.common.interfaces.ICollidable;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

public class CollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            if (!(entity1 instanceof ICollidable)) {
                continue;
            }

            for (Entity entity2 : world.getEntities()) {
                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }

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
/*
package dk.sdu.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.ICollidable;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IScore;
import dk.sdu.cbse.common.interfaces.IShot;

import java.util.ArrayList;
import java.util.List;

public class CollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entitiesToRemove = new ArrayList<>();

        for (Entity entity1 : world.getEntities()) {
            if (!(entity1 instanceof ICollidable)) {
                continue;
            }

            for (Entity entity2 : world.getEntities()) {
                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }

                if (!(entity2 instanceof ICollidable)) {
                    continue;
                }

                if (collides(entity1, entity2)) {
                    handleCollision(entity1, entity2, gameData, entitiesToRemove);
                }
            }
        }

        for (Entity entity : entitiesToRemove) {
            world.removeEntity(entity);
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    protected void handleCollision(Entity entity1, Entity entity2, GameData gameData, List<Entity> entitiesToRemove) {
        if (!entitiesToRemove.contains(entity1)) {
            entitiesToRemove.add(entity1);
        }

        if (!entitiesToRemove.contains(entity2)) {
            entitiesToRemove.add(entity2);
        }

        if (entity1 instanceof IShot && entity2 instanceof IScore scoreValue) {
            gameData.addScore(scoreValue.getScoreValue());
        }

        if (entity2 instanceof IShot && entity1 instanceof IScore scoreValue) {
            gameData.addScore(scoreValue.getScoreValue());
        }
    }
}

 */