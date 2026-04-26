module Core {
    requires Common;

    requires javafx.graphics;
    requires javafx.controls;

    requires spring.context;
    requires spring.beans;
    requires spring.core;

    uses dk.sdu.cbse.common.interfaces.IGamePluginService;
    uses dk.sdu.cbse.common.interfaces.IEntityProcessingService;

    opens dk.sdu.cbse.main to spring.core, spring.beans, spring.context;

    exports dk.sdu.cbse.main;
}