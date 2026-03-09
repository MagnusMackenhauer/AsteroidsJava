module Player {
    requires Common;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.player.PlayerPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.player.PlayerControlSystem;
}