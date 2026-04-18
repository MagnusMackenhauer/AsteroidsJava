package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.World;
import dk.sdu.commonasteroid.Asteroid;
import dk.sdu.commonasteroid.IAsteroidSplitter;

public class AsteroidSplitterSystem implements IAsteroidSplitter {

    @Override
    public void splitAsteroid(Entity asteroid, World world) {
        if (asteroid.getRadius() < 3) {
            world.removeEntity(asteroid);
            return;
        }

        double newRadius = asteroid.getRadius() / 2;

        Asteroid child1 = new Asteroid();
        child1.setRadius(newRadius);
        child1.setX(asteroid.getX());
        child1.setY(asteroid.getY());
        child1.setDx(asteroid.getDy() * -1);
        child1.setDy(asteroid.getDx());
        child1.setShape(asteroid.getShape());

        Asteroid child2 = new Asteroid();
        child2.setRadius(newRadius);
        child2.setX(asteroid.getX());
        child2.setY(asteroid.getY());
        child2.setDx(asteroid.getDy());
        child2.setDy(asteroid.getDx() * -1);
        child2.setShape(asteroid.getShape());

        world.addEntity(child1);
        world.addEntity(child2);
        world.removeEntity(asteroid);
    }
}
