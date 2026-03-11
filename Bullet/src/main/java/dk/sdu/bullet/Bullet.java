package dk.sdu.bullet;

import dk.sdu.cbse.common.Entity;

public class Bullet extends Entity {

    // hvor mange frames bullet eksisterer
    private int life = 120;

    public int getLife() {
        return life;
    }

    public void decreaseLife() {
        life--;
    }
}
