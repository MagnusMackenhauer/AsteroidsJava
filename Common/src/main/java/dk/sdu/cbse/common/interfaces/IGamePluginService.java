package dk.sdu.cbse.common.interfaces;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}