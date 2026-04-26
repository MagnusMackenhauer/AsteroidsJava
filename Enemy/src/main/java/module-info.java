module Enemy {
    requires Common;
    requires CommonEnemy;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.enemy.EnemyPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.enemy.EnemyControlSystem;
}