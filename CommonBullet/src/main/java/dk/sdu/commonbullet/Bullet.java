package dk.sdu.commonbullet;

import dk.sdu.cbse.common.Entity;


public class Bullet extends Entity {

    private int life = 120;

    public int getLife() { return life; }
    public void decreaseLife() { life--; }

}
