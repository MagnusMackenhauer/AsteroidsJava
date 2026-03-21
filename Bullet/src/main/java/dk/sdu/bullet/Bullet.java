package dk.sdu.bullet;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.interfaces.ICollidable;
import dk.sdu.cbse.common.interfaces.IProjectile;
import dk.sdu.cbse.common.interfaces.IScore;
import dk.sdu.cbse.common.interfaces.IShot;

public class Bullet extends Entity implements ICollidable, IShot, IProjectile {

    // hvor mange frames bullet eksisterer
    private int life = 120;

    public int getLife() {
        return life;
    }

    public void decreaseLife() {
        life--;
    }
}
