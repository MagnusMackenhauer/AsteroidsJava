module Bullet {
    exports dk.sdu.bullet;

    requires Common;
    requires CommonBullet;

    provides dk.sdu.commonbullet.BulletSPI
            with dk.sdu.bullet.BulletControlSystem;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.bullet.BulletControlSystem;
}