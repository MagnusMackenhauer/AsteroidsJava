package dk.sdu.cbse.common.interfaces;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;

public interface IEntityProcessingService {

    // Kaldes hver frame i spillet
    // Her skal logikken ligge for at opdatere entities
    void process(GameData gameData, World world);
}