package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.commonasteroid.Asteroid;
import dk.sdu.commonasteroid.IAsteroidSplitter;

public class AsteroidSplitterSystem implements IAsteroidSplitter {

    @Override
    public void splitAsteroid(Entity asteroid, World world) {
        if (asteroid.getRadius() < 3) {
            world.removeEntity(asteroid);
            return;
        }

        PositionPart parentPos = asteroid.getPart(PositionPart.class);
        MovingPart parentMov = asteroid.getPart(MovingPart.class);
        double newRadius = asteroid.getRadius() / 2;

        Asteroid child1 = new Asteroid();
        child1.setRadius(newRadius);
        child1.setShape(asteroid.getShape());
        child1.add(new PositionPart(parentPos.getX(), parentPos.getY(), parentPos.getRotation()));
        MovingPart mov1 = new MovingPart(1.0, 0, 2, 0.5);
        mov1.setDx(parentMov.getDy() * -1);
        mov1.setDy(parentMov.getDx());
        child1.add(mov1);

        Asteroid child2 = new Asteroid();
        child2.setRadius(newRadius);
        child2.setShape(asteroid.getShape());
        child2.add(new PositionPart(parentPos.getX(), parentPos.getY(), parentPos.getRotation()));
        MovingPart mov2 = new MovingPart(1.0, 0, 2, 0.5);
        mov2.setDx(parentMov.getDy());
        mov2.setDy(parentMov.getDx() * -1);
        child2.add(mov2);

        world.addEntity(child1);
        world.addEntity(child2);
        world.removeEntity(asteroid);
    }
}
