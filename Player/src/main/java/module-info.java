module Player {
    requires Common;
    requires Bullet;

    uses dk.sdu.cbse.common.interfaces.IBulletSPI;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.player.PlayerPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.player.PlayerControlSystem;
}