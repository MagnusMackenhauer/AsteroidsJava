package dk.sdu.cbse.common.entityparts;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;

public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
