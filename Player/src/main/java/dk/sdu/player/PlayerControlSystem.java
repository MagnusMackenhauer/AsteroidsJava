package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        // Gennemgår alle entities i verden
        for (Entity entity : world.getEntities()) {

            // Vi vil kun styre Player-entities
            if (entity instanceof Player) {

                // Hvis venstre pil er nede, roter venstre
                if (gameData.getKeys().contains("LEFT")) {
                    entity.setRotation(entity.getRotation() - 3);
                }

                // Hvis højre pil er nede, roter højre
                if (gameData.getKeys().contains("RIGHT")) {
                    entity.setRotation(entity.getRotation() + 3);
                }

                if (gameData.getKeys().contains("UP")) {
                    double radians = Math.toRadians(entity.getRotation() - 90);
                    entity.setDx(entity.getDx() + Math.cos(radians) * 0.1);
                    entity.setDy(entity.getDy() + Math.sin(radians) * 0.1);
                }

                // Opdater position ud fra hastighed
                entity.setX(entity.getX() + entity.getDx());
                entity.setY(entity.getY() + entity.getDy());

                // Friktion - gør at skibet langsomt stopper
                entity.setDx(entity.getDx() * 0.98);
                entity.setDy(entity.getDy() * 0.98);

                // Screen wrap:
                // Hvis spilleren flyver ud i den ene side, kommer den ind i den modsatte side
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