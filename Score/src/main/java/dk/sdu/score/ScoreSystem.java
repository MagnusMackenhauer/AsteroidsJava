package dk.sdu.score;

import dk.sdu.cbse.common.CollisionEvent;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IProjectile;
import dk.sdu.cbse.common.interfaces.IScore;

public class ScoreSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (CollisionEvent event : world.getCollisionEvents()) {
            Entity e1 = event.getEntity1();
            Entity e2 = event.getEntity2();

            if (e1 instanceof IProjectile && e2 instanceof IScore scoreEntity) {
                gameData.addScore(scoreEntity.getScoreValue());
            }

            if (e2 instanceof IProjectile && e1 instanceof IScore scoreEntity) {
                gameData.addScore(scoreEntity.getScoreValue());
            }
        }
    }
}