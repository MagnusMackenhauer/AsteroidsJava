package dk.sdu.player;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    // Reference til spilleren som plugin'et opretter
    private Player player;

    @Override
    public void start(GameData gameData, World world) {

        // Opretter spilleren
        player = createPlayer(gameData);

        // Tilføjer spilleren til verden
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {

        // Fjerner spilleren fra verden når plugin'et stoppes
        world.removeEntity(player);
    }

    // Hjælpemetode der opretter spilleren
    private Player createPlayer(GameData gameData) {
        Player player = new Player();

        // Starter midt på skærmen
        player.setX(gameData.getDisplayWidth() / 2.0);
        player.setY(gameData.getDisplayHeight() / 2.0);

        // Startrotation
        // 270 grader gør typisk at trekanten peger opad med den måde polygonen er defineret på
        player.setRotation(270);

        // En simpel trekant som repræsenterer rocket ship
        // Formatet er x1,y1,x2,y2,x3,y3
        player.setShape(new double[] {
                0.0, -10.0,
                -5.0, 5.0,
                5.0, 5.0
        });

        return player;
    }
}