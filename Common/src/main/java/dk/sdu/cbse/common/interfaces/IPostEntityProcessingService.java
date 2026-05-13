package dk.sdu.cbse.common.interfaces;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
