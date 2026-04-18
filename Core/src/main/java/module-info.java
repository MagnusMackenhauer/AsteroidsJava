module Core {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    requires Player;
    requires Bullet;
    requires Asteroid;
    requires Collision;

    requires javafx.graphics;
    requires javafx.controls;

    uses dk.sdu.cbse.common.interfaces.IGamePluginService;
    uses dk.sdu.cbse.common.interfaces.IEntityProcessingService;
    uses dk.sdu.commonbullet.BulletSPI;

    exports dk.sdu.cbse.main;
}