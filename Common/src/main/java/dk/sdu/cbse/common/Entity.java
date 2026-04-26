package dk.sdu.cbse.common;

import dk.sdu.cbse.common.entityparts.EntityPart;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity {

    private final String id = UUID.randomUUID().toString();

    private double radius;
    private double[] shape;
    private boolean isHit = false;

    private final Map<Class, EntityPart> parts = new ConcurrentHashMap<>();

    public String getId() { return id; }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    public double[] getShape() { return shape; }
    public void setShape(double[] shape) { this.shape = shape; }

    public boolean isHit() { return isHit; }
    public void setIsHit(boolean isHit) { this.isHit = isHit; }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class<E> partClass) {
        return partClass.cast(parts.get(partClass));
    }

    public void processAllParts(GameData gameData) {
        for (EntityPart part : parts.values()) {
            part.process(gameData, this);
        }
    }

    private int collisionGroup = 0;

    public int getCollisionGroup() { return collisionGroup; }
    public void setCollisionGroup(int collisionGroup) { this.collisionGroup = collisionGroup; }
}
