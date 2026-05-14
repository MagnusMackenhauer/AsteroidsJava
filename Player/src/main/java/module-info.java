module Player {
    requires Common;
    requires CommonBullet;
    requires CommonScore;

    uses dk.sdu.commonbullet.BulletSPI;
    uses dk.sdu.commonscore.ScoreSPI;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.player.PlayerPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.player.PlayerControlSystem;
}