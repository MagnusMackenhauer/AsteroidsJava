package dk.sdu.collision;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.commonasteroid.Asteroid;
import dk.sdu.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionSystemTest {

    private CollisionSystem collisionSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    void setUp() {
        collisionSystem = new CollisionSystem();
        gameData = new GameData();
        world = new World();
    }

    @Test
    void entitiesThatOverlapShouldBeHit() {
        Player player = new Player();
        player.setRadius(10);
        player.setCollisionGroup(1);
        player.add(new PositionPart(100, 100, 0));

        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(10);
        asteroid.setCollisionGroup(2);
        asteroid.add(new PositionPart(105, 100, 0));

        world.addEntity(player);
        world.addEntity(asteroid);

        collisionSystem.process(gameData, world);

        assertTrue(player.isHit());
        assertTrue(asteroid.isHit());
    }

    @Test
    void entitiesFarApartShouldNotBeHit() {
        Player player = new Player();
        player.setRadius(10);
        player.setCollisionGroup(1);
        player.add(new PositionPart(100, 100, 0));

        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(10);
        asteroid.setCollisionGroup(2);
        asteroid.add(new PositionPart(500, 500, 0));

        world.addEntity(player);
        world.addEntity(asteroid);

        collisionSystem.process(gameData, world);

        assertFalse(player.isHit());
        assertFalse(asteroid.isHit());
    }

    @Test
    void sameGroupShouldNotCollide() {
        Asteroid a1 = new Asteroid();
        a1.setRadius(10);
        a1.setCollisionGroup(2);
        a1.add(new PositionPart(100, 100, 0));

        Asteroid a2 = new Asteroid();
        a2.setRadius(10);
        a2.setCollisionGroup(2);
        a2.add(new PositionPart(105, 100, 0));

        world.addEntity(a1);
        world.addEntity(a2);

        collisionSystem.process(gameData, world);

        assertFalse(a1.isHit());
        assertFalse(a2.isHit());
    }
}