package dk.sdu.cbse.common;


import java.util.UUID;

public class Entity {

    private final String id = UUID.randomUUID().toString();

    private double x;
    private double y;
    private double rotation;
    private double dx;
    private double dy;
    private double radius;

    private double[] shape;

    public String  getId() {
        return id;
    }

    // Returnerer x-position
    public double getX() {
        return x;
    }

    // Sætter x-position
    public void setX(double x) {
        this.x = x;
    }

    // Returnerer y-position
    public double getY() {
        return y;
    }

    // Sætter y-position
    public void setY(double y) {
        this.y = y;
    }

    // Returnerer rotation
    public double getRotation() {
        return rotation;
    }

    // Sætter rotation
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    // Returnerer fart i x-retning
    public double getDx() {
        return dx;
    }

    // Sætter fart i x-retning
    public void setDx(double dx) {
        this.dx = dx;
    }

    // Returnerer fart i y-retning
    public double getDy() {
        return dy;
    }

    // Sætter fart i y-retning
    public void setDy(double dy) {
        this.dy = dy;
    }

    // Returnerer polygonens punkter
    public double[] getShape() {
        return shape;
    }

    // Sætter polygonens punkter
    public void setShape(double[] shape) {
        this.shape = shape;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    private boolean isHit = false;

    public boolean isHit() { return isHit; }
    public void setIsHit(boolean isHit) { this.isHit = isHit; }





}
