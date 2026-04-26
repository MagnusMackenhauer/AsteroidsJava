package dk.sdu.cbse.common;

import java.util.HashSet;
import java.util.Set;

public class GameData {

    private int displayWidth = 1000;
    private int displayHeight = 1000;
    private final Set<String> keys = new HashSet<>();

    public int getDisplayWidth() { return displayWidth; }
    public void setDisplayWidth(int displayWidth) { this.displayWidth = displayWidth; }

    public int getDisplayHeight() { return displayHeight; }
    public void setDisplayHeight(int displayHeight) { this.displayHeight = displayHeight; }

    public Set<String> getKeys() { return keys; }


}
