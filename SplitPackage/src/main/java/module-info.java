import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module SplitPackage {
    requires Common;

    exports dk.sdu.mmmi.cbse.collisionsystem;

    provides IGamePluginService with dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
}