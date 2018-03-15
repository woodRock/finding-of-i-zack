package Entities;

import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.Util.GameDimensions;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by allanbenj1 on 27/09/17.
 */
public class EnemyTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);
    Player p = new Player(location);

    @Test
    public void testDamage() {
        Enemy e = new Enemy(location, p);
        e.damage(10);
    }

    @Test
    public void testDrawEnemy() throws InterruptedException {
        Enemy enemy = new Enemy(location, p);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> enemy.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }


}
