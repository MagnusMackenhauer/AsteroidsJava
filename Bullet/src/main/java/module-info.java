module Bullet {
    exports dk.sdu.bullet;

    requires Common;

    provides dk.sdu.cbse.common.interfaces.IBulletSPI
            with dk.sdu.bullet.BulletControlSystem;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.bullet.BulletControlSystem;
}