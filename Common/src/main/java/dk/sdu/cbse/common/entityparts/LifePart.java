package dk.sdu.cbse.common.entityparts;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;

public class LifePart implements EntityPart {

    private int life;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() { return life; }

    public boolean isExpired() { return life <= 0; }

    @Override
    public void process(GameData gameData, Entity entity) {
        life--;
    }
}
