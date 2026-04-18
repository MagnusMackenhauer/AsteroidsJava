module Asteroid {
    exports dk.sdu.asteroid;
    requires Common;
    requires CommonAsteroid;

    uses dk.sdu.commonasteroid.IAsteroidSplitter;

    provides dk.sdu.cbse.common.interfaces.IGamePluginService
            with dk.sdu.asteroid.AsteroidPlugin;

    provides dk.sdu.cbse.common.interfaces.IEntityProcessingService
            with dk.sdu.asteroid.AsteroidControlSystem;

    provides dk.sdu.commonasteroid.IAsteroidSplitter
            with dk.sdu.asteroid.AsteroidSplitterSystem;
}