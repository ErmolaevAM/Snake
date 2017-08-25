package model;

import utils.Directions;
import view.GamePanel;

/**
 * Created by Александр on 24.08.2017.
 */
public class Snake {

    private int[] x;
    private int[] y;

    private int length;
    private Directions direction;

    public Snake(int max_size, int x0, int x1, int y0, int y1) {
        x = new int[max_size];
        y = new int[max_size];

        x[0] = x0;
        x[1] = x1;
        y[0] = y0;
        y[1] = y1;

        length = 2;
        direction = Directions.LEFT;
    }

    public void move() {
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (direction == Directions.UP) y[0]--;
        if (direction == Directions.DOWN) y[0]++;
        if (direction == Directions.LEFT) x[0]--;
        if (direction == Directions.RIGHT) x[0]++;

        if (x[0] > GamePanel.WIDTH-1) x[0] = 0;
        if (x[0] < 0) x[0] = GamePanel.WIDTH-1;
        if (y[0] > GamePanel.HEIGHT-1) y[0] = 0;
        if (y[0] < 0) y[0] = GamePanel.HEIGHT-1;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void incrementLength() {
        this.length += 1;
    }

    public void incrementLength(int increment) {
        this.length += increment;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
