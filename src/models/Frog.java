package models;

import utils.FrogType;
import view.GamePanel;

import java.util.Random;

/**
 * Created by Александр on 24.08.2017.
 */
public class Frog {

    private int x;
    private int y;

    private FrogType type;

    public Frog() {
        changeCoordinates();
    }

    public void changeCoordinates() {
        Random r = new Random();
        type = FrogType.values()[r.nextInt(FrogType.values().length)];
        x = r.nextInt(GamePanel.WIDTH);
        y = r.nextInt(GamePanel.HEIGHT);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public FrogType getType() {
        return type;
    }

    public void setType(FrogType type) {
        this.type = type;
    }
}
