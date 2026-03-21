module Core {
    requires Common;
    requires Player;
    requires Bullet;
    requires Asteroid;

    requires javafx.graphics;
    requires javafx.controls;

    uses dk.sdu.cbse.common.interfaces.IGamePluginService;
    uses dk.sdu.cbse.common.interfaces.IEntityProcessingService;

    exports dk.sdu.cbse.main;
}