package dk.sdu.cbse.common;

public class CollisionEvent {
    private final Entity entity1;
    private final Entity entity2;

    public CollisionEvent(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Entity getEntity1() {
        return entity1;
    }

    public Entity getEntity2() {
        return entity2;
    }
}