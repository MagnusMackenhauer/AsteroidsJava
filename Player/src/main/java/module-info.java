module Player {
    requires Common;
    requires CommonBullet;

    uses dk.sdu.commonbullet.BulletSPI;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.player.PlayerPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.player.PlayerControlSystem;
}