package dk.sdu.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CollisionSystemMockTest {

    @Mock
    private Entity entity1;

    @Mock
    private Entity entity2;

    @Mock
    private World mockWorld;

    private CollisionSystem collisionSystem;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        collisionSystem = new CollisionSystem();
        gameData = new GameData();

        when(entity1.getId()).thenReturn("id-1");
        when(entity2.getId()).thenReturn("id-2");
        when(mockWorld.getEntities()).thenReturn(List.of(entity1, entity2));
    }

    @Test
    void collidingEntitiesOfDifferentGroupsShouldBeMarkedHit() {
        when(entity1.getPart(PositionPart.class)).thenReturn(new PositionPart(100, 100, 0));
        when(entity2.getPart(PositionPart.class)).thenReturn(new PositionPart(105, 100, 0));
        when(entity1.getRadius()).thenReturn(10.0);
        when(entity2.getRadius()).thenReturn(10.0);
        when(entity1.getCollisionGroup()).thenReturn(1);
        when(entity2.getCollisionGroup()).thenReturn(2);

        collisionSystem.process(gameData, mockWorld);

        verify(entity1, atLeastOnce()).setIsHit(true);
        verify(entity2, atLeastOnce()).setIsHit(true);
    }

    @Test
    void entitiesOfSameGroupShouldNotBeMarkedHit() {
        when(entity1.getPart(PositionPart.class)).thenReturn(new PositionPart(100, 100, 0));
        when(entity2.getPart(PositionPart.class)).thenReturn(new PositionPart(105, 100, 0));
        when(entity1.getRadius()).thenReturn(10.0);
        when(entity2.getRadius()).thenReturn(10.0);
        when(entity1.getCollisionGroup()).thenReturn(2);
        when(entity2.getCollisionGroup()).thenReturn(2);

        collisionSystem.process(gameData, mockWorld);

        verify(entity1, never()).setIsHit(true);
        verify(entity2, never()).setIsHit(true);
    }

    @Test
    void entitiesFarApartShouldNotBeMarkedHit() {
        when(entity1.getPart(PositionPart.class)).thenReturn(new PositionPart(100, 100, 0));
        when(entity2.getPart(PositionPart.class)).thenReturn(new PositionPart(500, 500, 0));
        when(entity1.getRadius()).thenReturn(10.0);
        when(entity2.getRadius()).thenReturn(10.0);

        collisionSystem.process(gameData, mockWorld);

        verify(entity1, never()).setIsHit(true);
        verify(entity2, never()).setIsHit(true);
    }
}