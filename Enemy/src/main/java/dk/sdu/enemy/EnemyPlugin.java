package dk.sdu.enemy;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import dk.sdu.commonenemy.Enemy;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {

    private Enemy enemy;
    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    private Enemy createEnemy(GameData gameData) {
        Enemy enemy = new Enemy();

        enemy.setRadius(20);
        enemy.setShape(new double[]{
                0.0, -20.0,
                -14.0, 14.0,
                0.0, 6.0,
                14.0, 14.0
        });

        enemy.add(new PositionPart(
                random.nextInt(gameData.getDisplayWidth()),
                random.nextInt(gameData.getDisplayHeight()),
                random.nextInt(360)
        ));

        enemy.add(new MovingPart(0.99, 0.08, 3, 2));

        enemy.setCollisionGroup(2);

        return enemy;
    }
}