module Collision {
    requires Common;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.collision.CollisionSystem;
}