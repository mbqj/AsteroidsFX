package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class CollisionSystem implements IGamePluginService {


    @Override
    public void start(GameData gameData, World world) {
        System.out.println("CollisionSystem started");
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}