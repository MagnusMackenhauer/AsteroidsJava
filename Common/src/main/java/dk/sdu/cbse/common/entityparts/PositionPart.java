package dk.sdu.cbse.common.entityparts;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;

public class PositionPart implements EntityPart {

    private double x;
    private double y;
    private double rotation;

    public PositionPart(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getRotation() { return rotation; }
    public void setRotation(double rotation) { this.rotation = rotation; }

    @Override
    public void process(GameData gameData, Entity entity) {
        // Wraparound-logik — skrevet én gang, brugt af alle entities
        if (x < 0) x = gameData.getDisplayWidth();
        if (x > gameData.getDisplayWidth()) x = 0;
        if (y < 0) y = gameData.getDisplayHeight();
        if (y > gameData.getDisplayHeight()) y = 0;
    }
}
