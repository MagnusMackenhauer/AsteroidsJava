module Asteroid {
    exports dk.sdu.asteroid;
    requires Common;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.asteroid.AsteroidPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.asteroid.AsteroidControlSystem,
                    dk.sdu.asteroid.AsteroidCollisionSystem;
}