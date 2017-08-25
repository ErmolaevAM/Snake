package logic_magik;

import model.Frog;
import model.Snake;
import utils.Directions;
import utils.FrogType;
import view.GamePanel;

import java.awt.*;

/**
 * Created by Александр on 24.08.2017.
 */
public class GameLogic implements Runnable {

    private Snake snake;
    private FrogLogic frogLogic;

    private GamePanel panel;

    private Thread gameThread;

    private boolean pauseFlag;

    public GameLogic(GamePanel panel) {
        this.panel = panel;
        this.gameThread = new Thread(this);
        this.frogLogic = new FrogLogic(new Frog());
        this.snake = new Snake(GamePanel.WIDTH * GamePanel.HEIGHT, 10, 10, 9, 10);
    }

    public boolean isFrogLogicAlive() {
        return this.frogLogic.isAlive();
    }

    public void setFrogLogicAlive(boolean flag) {
        this.frogLogic.setAlive(flag);
    }

    public FrogType getFrogType() {
        return this.frogLogic.getFrogType();
    }

    public int getFrogX() {
        return this.frogLogic.getFrogX();
    }

    public int getFrogY() {
        return this.frogLogic.getFrogY();
    }

    public int[] getSnakeX() {
        return snake.getX();
    }

    public int[] getSnakeY() {
        return snake.getY();
    }

    public int getSnakeLength() {
        return snake.getLength();
    }

    public Directions getSnakeDirections() {
        return snake.getDirection();
    }

    public void setSnakeDirections(Directions directions) {
        snake.setDirection(directions);
    }

    public void startGameThread() {
        gameThread.start();
    }

    public void interruptGameThread() {
        gameThread.interrupt();
    }

    public void startFrogLogicThread() {
        frogLogic.startFrogLogicThread();
    }

    public void interruptFrogLogicThread() {
        frogLogic.interruptFrogLogicThread();
    }

    public boolean isPauseFlag() {
        return pauseFlag;
    }

    public void setPauseFlag(boolean pauseFlag) {
        this.pauseFlag = pauseFlag;
    }

    public void restartGame() {
        snake.setLength(2);
        int[] x = new int[GamePanel.HEIGHT*GamePanel.WIDTH];
        x[0] = 10;
        x[1] = 10;
        int[] y = new int[GamePanel.HEIGHT*GamePanel.WIDTH];
        y[0] = 9;
        y[1] = 10;
        snake.setX(x);
        snake.setY(y);
        pauseFlag = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (pauseFlag) {
                try {
                    Thread.currentThread().sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                snake.move();
                Rectangle snkRectangle = new Rectangle(snake.getX()[0] * GamePanel.SCALE + 5,
                        snake.getY()[0] * GamePanel.SCALE + 5, GamePanel.SCALE - 11, GamePanel.SCALE - 11);
                Rectangle frgRectangle = new Rectangle(frogLogic.getFrogX() * GamePanel.SCALE + 5,
                        frogLogic.getFrogY() * GamePanel.SCALE + 5, GamePanel.SCALE - 11, GamePanel.SCALE - 11);

                for (int i = 1; i < snake.getLength(); i++) {
                    Rectangle snkElem = new Rectangle(snake.getX()[i] * GamePanel.SCALE + 5,
                            snake.getY()[i] * GamePanel.SCALE + 5, GamePanel.SCALE - 11, GamePanel.SCALE - 11);
                    if (snkRectangle.intersects(snkElem)) {
                        Thread.currentThread().interrupt();
                        frogLogic.interruptFrogLogicThread();
                    }
                }

                if (snkRectangle.intersects(frgRectangle)) {
                    frogLogic.setAlive(false);
                    frogLogic.interruptFrogLogicThread();
                    frogLogic.createNewFrogLogicThread();
                    if (frogLogic.getFrogType() == FrogType.RED) {
                        frogLogic.interruptFrogLogicThread();
                        Thread.currentThread().interrupt();
                    } else {
                        int[] newX = snake.getX();
                        int[] newY = snake.getY();
                        newX[snake.getLength()] = newX[snake.getLength() - 1];
                        newY[snake.getLength()] = newY[snake.getLength() - 1];
                        snake.setX(newX);
                        snake.setY(newY);
                        if (frogLogic.getFrogType() == FrogType.GREEN) {
                            snake.setLength(snake.getLength() + 1);
                        } else if (frogLogic.getFrogType() == FrogType.BLUE) {
                            snake.setLength(snake.getLength() + 2);
                        }
                    }
                    System.out.println("Snake length = " + snake.getLength());
                }
                panel.repaint();
            }
        }
    }
}










