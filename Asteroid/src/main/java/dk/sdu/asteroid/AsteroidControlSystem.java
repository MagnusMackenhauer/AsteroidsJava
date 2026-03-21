package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid) {

                entity.setX(entity.getX() + entity.getDx());
                entity.setY(entity.getY() + entity.getDy());

                entity.setRotation(entity.getRotation() + 0.5);

                if (entity.getX() < 0) {
                    entity.setX(gameData.getDisplayWidth());
                }

                if (entity.getX() > gameData.getDisplayWidth()) {
                    entity.setX(0);
                }

                if (entity.getY() < 0) {
                    entity.setY(gameData.getDisplayHeight());
                }

                if (entity.getY() > gameData.getDisplayHeight()) {
                    entity.setY(0);
                }
            }
        }
    }
}