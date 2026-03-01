package dk.sdu.cbse.common;

public class GameData {

    private int displayWidth = 1000;

    private int displayHeight = 1000;

    private int asteroidsDestroyed = 0;

    public int getDisplayWidth() {return displayWidth;}

    public void setDisplayWidth(int displayWidth) {this.displayWidth = displayWidth;}

    public int getDisplayHeight() {return displayHeight;}

    public void setDisplayHeight(int displayHeight) {this.displayHeight = displayHeight;}

    public int getAsteroidsDestroyed() {return asteroidsDestroyed; }

    public void setAsteroidsDestroyed(int asteroidsDestroyed)
    {
        this.asteroidsDestroyed = asteroidsDestroyed;
    }

    public void incrementAsteroidsDestroyed()
    {
        asteroidsDestroyed++;
    }

}

