import view.GamePanel;

import javax.swing.*;

/**
 * Created by Александр on 24.08.2017.
 */
public class Application {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel();
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(panel.WIDTH*panel.SCALE+7, panel.HEIGHT*panel.SCALE+30);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }
}
