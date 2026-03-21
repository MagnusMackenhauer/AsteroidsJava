package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.CollisionEvent;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IProjectile;

public class AsteroidCollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (CollisionEvent event : world.getCollisionEvents()) {
            Entity e1 = event.getEntity1();
            Entity e2 = event.getEntity2();

            if (e1 instanceof Asteroid && e2 instanceof IProjectile) {
                world.removeEntity(e1);
                world.removeEntity(e2);
                /*gameData.addScore(1);*/
            }

            if (e2 instanceof Asteroid && e1 instanceof IProjectile) {
                world.removeEntity(e2);
                world.removeEntity(e1);
                /*gameData.addScore(1);*/
            }
        }
    }
}