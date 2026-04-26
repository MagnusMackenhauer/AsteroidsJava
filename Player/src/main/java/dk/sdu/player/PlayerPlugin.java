package dk.sdu.player;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;

public class PlayerPlugin implements IGamePluginService {

    private Player player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private Player createPlayer(GameData gameData) {
        Player player = new Player();

        player.setRadius(8);
        player.setShape(new double[]{
                0.0, -10.0,
                -5.0, 5.0,
                5.0, 5.0
        });

        player.add(new PositionPart(
                gameData.getDisplayWidth() / 2.0,
                gameData.getDisplayHeight() / 2.0,
                270
        ));

        player.add(new MovingPart(0.98, 0.1, 5, 3));

        player.setCollisionGroup(1);

        return player;
    }
}
