package dk.sdu.cbse.common.interfaces;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;

public interface IGamePluginService {

    // Kaldes når spillet starter
    // Her kan plugin'et oprette og tilføje entities til verden
    void start(GameData gameData, World world);

    // Kaldes når spillet stopper
    // Her kan plugin'et rydde op og fjerne sine entities
    void stop(GameData gameData, World world);
}