package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * Precondition: The game data and world is not null
     * Postcondition: The entity has run their start logic
     *
     * @param gameData the game data which contains the size of the screen and inputs is passed to the process method
     * @param world the world which contains the entities is passed to the process method
     * @throws
     */

    void start(GameData gameData, World world);

    /**
     * Precondition: The game data and world is not null
     * postcondition: The entity has stopped
     *
     * @param gameData the game data which contains the size of the screen and inputs is passed to the process method
     * @param world the world which contains the entities is passed to the process method
     * @throws
     */
    void stop(GameData gameData, World world);
}
