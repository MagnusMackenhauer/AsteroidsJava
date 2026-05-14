module Core {
    requires Common;

    requires javafx.graphics;
    requires javafx.controls;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.desktop;
    requires java.net.http;

    uses dk.sdu.cbse.common.interfaces.IGamePluginService;
    uses dk.sdu.cbse.common.interfaces.IEntityProcessingService;
    uses dk.sdu.cbse.common.interfaces.IPostEntityProcessingService;

    opens dk.sdu.cbse.main to spring.core, spring.beans, spring.context;

    exports dk.sdu.cbse.main;
}