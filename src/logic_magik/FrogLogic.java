package logic_magik;

import models.Frog;
import utils.FrogType;

/**
 * Created by Александр on 24.08.2017.
 */
public class FrogLogic implements Runnable {

    private Frog frog;

    private boolean alive;

    private Thread frogThread;

    public FrogLogic(Frog frog) {
        this.frog = frog;
        this.alive = true;
        frogThread = new Thread(this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frog.changeCoordinates();
            alive = true;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Thread getFrogThread() {
        return frogThread;
    }

    public void setFrogThread(Thread frogThread) {
        this.frogThread = frogThread;
    }

    public int getFrogX() {
        return frog.getX();
    }

    public int getFrogY() {
        return frog.getY();
    }

    public FrogType getFrogType() {
        return frog.getType();
    }

    public void startFrogLogicThread() {
        this.frogThread.start();
    }

    public void interruptFrogLogicThread() {
        this.frogThread.interrupt();
    }

    public void createNewFrogLogicThread() {
        this.frogThread = new Thread(this);
    }
}
