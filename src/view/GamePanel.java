package view;

import logic_magik.GameLogic;
import utils.Direction;
import utils.FrogType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Александр on 24.08.2017.
 */
public class GamePanel extends JPanel {
    public static final int SCALE = 32;
    public static final int WIDTH = 30;
    public static final int HEIGHT= 20;

    private GameLogic gameLogic;

    public GamePanel() {
        gameLogic = new GameLogic(this);

        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_UP) && (gameLogic.getSnakeDirections() != Direction.DOWN)) gameLogic.setSnakeDirections(Direction.UP);
                if ((key == KeyEvent.VK_DOWN) && (gameLogic.getSnakeDirections() != Direction.UP)) gameLogic.setSnakeDirections(Direction.DOWN);
                if ((key == KeyEvent.VK_LEFT) && (gameLogic.getSnakeDirections() != Direction.RIGHT)) gameLogic.setSnakeDirections(Direction.LEFT);
                if ((key == KeyEvent.VK_RIGHT) && (gameLogic.getSnakeDirections() != Direction.LEFT)) gameLogic.setSnakeDirections(Direction.RIGHT);
                if (key == KeyEvent.VK_SPACE) gameLogic.setPauseFlag(!gameLogic.isPauseFlag());
                if (key == KeyEvent.VK_ESCAPE) {
                    gameLogic.restartGame();
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        gameLogic.startFrogLogicThread();
        gameLogic.startGameThread();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //заливка фона
        g.setColor(new Color(154, 154, 154));
        g.fillRect(0,0,SCALE*WIDTH, SCALE*HEIGHT);

        //отрисовка сетки
        g.setColor(new Color(0,0,0));
        for (int i = 0; i <= WIDTH; i++) {
            g.drawLine(i*SCALE,0,i*SCALE,HEIGHT*SCALE);
        }
        for (int i = 0; i <= HEIGHT; i++) {
            g.drawLine(0,i*SCALE,WIDTH*SCALE,i*SCALE);
        }

        //отрисовка лягушки
        if (gameLogic.isFrogLogicAlive()) {
            if (gameLogic.getFrogType() == FrogType.RED) g.setColor(Color.RED);
            else if (gameLogic.getFrogType() == FrogType.GREEN) g.setColor(Color.GREEN);
            else g.setColor(Color.BLUE);

            g.fillOval(gameLogic.getFrogX()*SCALE+5, gameLogic.getFrogY()*SCALE+5,
                    SCALE-11, SCALE-11);
        }

        //отрисовка змеи
        g.setColor(new Color(0,0,0));
        g.fillRect(gameLogic.getSnakeX()[0]*SCALE+5, gameLogic.getSnakeY()[0]*SCALE+5,
                SCALE-11, SCALE-11);
        g.setColor(new Color(77, 77, 77));
        for (int i = 1; i < gameLogic.getSnakeLength(); i++) {
            g.fillRect(gameLogic.getSnakeX()[i]*SCALE+5, gameLogic.getSnakeY()[i]*SCALE+5,
                    SCALE-11, SCALE-11);
        }
    }
}















