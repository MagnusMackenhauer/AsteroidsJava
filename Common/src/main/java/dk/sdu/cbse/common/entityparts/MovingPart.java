package dk.sdu.cbse.common.entityparts;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;

public class MovingPart implements EntityPart {

    private double dx;
    private double dy;
    private double deceleration;
    private double acceleration;
    private double maxSpeed;
    private double rotationSpeed;
    private boolean left, right, up;

    public MovingPart(double deceleration, double acceleration, double maxSpeed, double rotationSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.rotationSpeed = rotationSpeed;
    }

    public double getDx() { return dx; }
    public void setDx(double dx) { this.dx = dx; }

    public double getDy() { return dy; }
    public void setDy(double dy) { this.dy = dy; }

    public void setLeft(boolean left) { this.left = left; }
    public void setRight(boolean right) { this.right = right; }
    public void setUp(boolean up) { this.up = up; }

    public void setSpeed(double speed) {
        this.acceleration = speed;
        this.maxSpeed = speed;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart pos = entity.getPart(PositionPart.class);

        // Rotation
        if (left) pos.setRotation(pos.getRotation() - rotationSpeed);
        if (right) pos.setRotation(pos.getRotation() + rotationSpeed);

        // Acceleration
        if (up) {
            double radians = Math.toRadians(pos.getRotation() - 90);
            dx += Math.cos(radians) * acceleration;
            dy += Math.sin(radians) * acceleration;
        }

        // Deceleration
        dx *= deceleration;
        dy *= deceleration;

        // Max speed
        double speed = Math.sqrt(dx * dx + dy * dy);
        if (speed > maxSpeed) {
            dx = (dx / speed) * maxSpeed;
            dy = (dy / speed) * maxSpeed;
        }

        // Opdater position
        pos.setX(pos.getX() + dx);
        pos.setY(pos.getY() + dy);
    }
}
