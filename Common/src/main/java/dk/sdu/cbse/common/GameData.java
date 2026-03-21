package dk.sdu.cbse.common;

import java.util.HashSet;
import java.util.Set;

public class GameData {

    // Skærmens bredde
    private int displayWidth = 1000;

    // Skærmens højde
    private int displayHeight = 1000;

    // Her gemmes hvilke taster der er trykket ned lige nu
    // Fx "LEFT", "RIGHT", "UP"
    private final Set<String> keys = new HashSet<>();

    // Returnerer skærmens bredde
    public int getDisplayWidth() {
        return displayWidth;
    }

    // Sætter skærmens bredde
    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    // Returnerer skærmens højde
    public int getDisplayHeight() {
        return displayHeight;
    }

    // Sætter skærmens højde
    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    // Returnerer de taster der er nede
    public Set<String> getKeys() {
        return keys;
    }

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
}