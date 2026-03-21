module Collision {
    requires Common;
    requires Bullet;
    requires Asteroid;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.collision.CollisionSystem;
}