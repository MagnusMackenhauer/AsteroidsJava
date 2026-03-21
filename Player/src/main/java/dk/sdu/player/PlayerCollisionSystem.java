package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.CollisionEvent;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IEnemy;

public class PlayerCollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (CollisionEvent event : world.getCollisionEvents()) {
            Entity e1 = event.getEntity1();
            Entity e2 = event.getEntity2();

            if (e1 instanceof Player && e2 instanceof IEnemy) {
                world.removeEntity(e1);
                /*gameData.setGameOver(true);*/
            }

            if (e2 instanceof Player && e1 instanceof IEnemy) {
                world.removeEntity(e2);
                /*gameData.setGameOver(true);*/
            }
        }
    }
}