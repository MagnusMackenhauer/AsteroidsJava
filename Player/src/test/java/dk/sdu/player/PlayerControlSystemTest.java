package dk.sdu.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerControlSystemTest {

    @Mock
    private World mockWorld;

    private GameData gameData;
    private PlayerControlSystem system;
    private Player player;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
        system = new PlayerControlSystem();

        player = new Player();
        player.setRadius(8);
        player.add(new PositionPart(500, 500, 270));
        player.add(new MovingPart(0.98, 0.1, 5, 3));
        player.setCollisionGroup(1);

        when(mockWorld.getEntities()).thenReturn(List.of(player));
    }

    /*
    @Test
    void playerShouldAddBulletToWorldWhenSpaceIsPressed() {
        gameData.getKeys().add("SPACE");

        system.process(gameData, mockWorld);

        verify(mockWorld, atLeastOnce()).addEntity(any(Entity.class));
    }
    */


    @Test
    void playerShouldNotAddBulletWhenSpaceIsNotPressed() {
        system.process(gameData, mockWorld);

        verify(mockWorld, never()).addEntity(any(Entity.class));
    }
}