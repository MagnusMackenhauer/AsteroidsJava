package dk.sdu.commonasteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.World;

public interface IAsteroidSplitter {
    void splitAsteroid(Entity asteroid, World world);
}