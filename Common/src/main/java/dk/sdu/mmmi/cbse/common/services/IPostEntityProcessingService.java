package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public interface IPostEntityProcessingService {

    /**
     * Precondition: The game data and world is not null
     * postcondition: The entity has had their logic run
     *
     * @param gameData the game data which contains the size of the screen and inputs is passed to the process method
     * @param world the world which contains the entities is passed to the process method
     * @throws
     */
    void process(GameData gameData, World world);
}
