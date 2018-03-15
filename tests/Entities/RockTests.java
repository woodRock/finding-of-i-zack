package Entities;

import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Util.GameDimensions;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import TheFindingOfIZack.Util.Point;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by allanbenj1 on 16/10/17.
 */
public class RockTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);

    @Test
    public void testRock() {
        Rock r = new Rock(location);
        assertTrue(r != null);

        r.damage(r.getHealth());
        assertTrue(r.getHealth() == 0);
        assertTrue(r.isDestroyed());
    }

    @Test
    public void testDrawProjectile() throws InterruptedException {
        Rock r = new Rock(location);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> r.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

}
