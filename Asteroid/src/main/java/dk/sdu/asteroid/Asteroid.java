package dk.sdu.asteroid;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.interfaces.ICollidable;
import dk.sdu.cbse.common.interfaces.IEnemy;
import dk.sdu.cbse.common.interfaces.IScore;

public class Asteroid extends Entity implements ICollidable, IScore, IEnemy {

    @Override
    public int getScoreValue() {
        return 1;
    }
}