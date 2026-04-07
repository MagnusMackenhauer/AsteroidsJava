module Score {
    requires Common;

    exports dk.sdu.score;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.score.ScorePlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.score.ScoreSystem;
}