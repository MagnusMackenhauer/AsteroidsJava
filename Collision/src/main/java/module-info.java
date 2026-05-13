module Collision {
    requires Common;

    provides dk.sdu.cbse.common.interfaces.IPostEntityProcessingService
            with dk.sdu.collision.CollisionSystem;
}