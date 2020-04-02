package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Items.Weapon;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.World.Rooms.standardRoom;
import TheFindingOfIZack.Util.Point;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by allanbenj1 on 16/10/17.
 */
public class UrnTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);
    private Player p = new Player(location);

    @Test
    public void testUrn() {
        Urn u = new Urn(location, p);
        u.setItem(null);
        assertTrue(u != null);

        u.damage(u.getHealth());
        assertTrue(u.getHealth() == 0);
        assertTrue(u.isDestroyed());
    }

    @Test
    public void testItemDrop() {
        standardRoom r = new standardRoom();
        p.setRoom(r);
        Weapon i = new Weapon(p);
        Urn u = new Urn(location, p);
        u.setItem(i);
        assertTrue(u.getItem() != null);

        u.damage(u.getHealth());
        assertTrue(u.isDestroyed());
        assertTrue(!p.getRoom().getCollectibles().isEmpty());
    }

    @Test
    public void testDrawUrn() throws InterruptedException {
        Urn u = new Urn(location, p);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> u.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

}
