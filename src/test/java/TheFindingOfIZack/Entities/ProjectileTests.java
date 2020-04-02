package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.Point;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;
import static org.junit.Assert.assertTrue;

/**
 * Created by allanbenj1 on 16/10/17.
 */
public class ProjectileTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);

    @Test
    public void testCreation() {
        Projectile p1 = new Projectile(10, location, "left");
        Projectile p2 = new Projectile(10, location, "right");
        Projectile p3 = new Projectile(10, location, "up");
        Projectile p4 = new Projectile(10, location, "down");
        assertTrue(p1 != null);
        assertTrue(p2 != null);
        assertTrue(p3 != null);
        assertTrue(p4 != null);
    }

    @Test
    public void checkWallCollisions() {
        Projectile p = new Projectile(10, new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL), "left");
        p.move(); p.move();
        assertTrue(p.wallCollision());

        p = new Projectile(10, new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL), "up");
        p.move(); p.move();
        assertTrue(p.wallCollision());

        p = new Projectile(10, new Point(GameDimensions.RIGHT_WALL- DEFAULT_WIDTH, GameDimensions.TOP_WALL), "right");
        p.move(); p.move();
        assertTrue(p.wallCollision());

        p = new Projectile(10, new Point(GameDimensions.LEFT_WALL, GameDimensions.BOTTOM_WALL-DEFAULT_WIDTH), "down");
        p.move(); p.move();
        assertTrue(p.wallCollision());
    }

    @Test
    public void testEnemyCollisions() {
        Projectile p = new Projectile(10, location, "up");
        Enemy e = new Enemy(location, new Player(location));
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(e);
        p.enemyCollision(enemies);
        assertTrue(p.isDestroyed());

        enemies.clear();
        p = new Projectile(10, location, "up");

        Boss b = new Boss(location, new Player(location));
        enemies.add(b);
        p.enemyCollision(enemies);
        assertTrue(p.isDestroyed());

    }

    @Test
    public void testEntityCollisions() {
        Projectile p = new Projectile(10, location, "up");
        Urn u = new Urn(location, new Player(location));
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(u);
        p.entityCollision(entities);
        assertTrue(p.isDestroyed());

        entities.clear();
        p = new Projectile(10, location, "up");

        Rock r = new Rock(location);
        entities.add(r);
        p.entityCollision(entities);
        assertTrue(p.isDestroyed());
    }

    @Test
    public void testDrawProjectile() throws InterruptedException {
        Projectile p = new Projectile(10, location, "left");
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> p.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

}
