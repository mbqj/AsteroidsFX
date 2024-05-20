import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;

    exports dk.sdu.mmmi.cbse.collisionsystem;

    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
}