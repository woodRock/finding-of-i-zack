package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertTrue;

public class FollowingMobTests {
    @Test
    public void createNewMob() throws Exception {
        MobEnemy test;

        test = new MobEnemy(MobType.Standard);
        assertTrue(test.getDamage() == 10);
        assertTrue(test.getSpeed() == 3);
        assertTrue(test.getHealth() == 50);

        test = new MobEnemy(MobType.Fast);
        assertTrue(test.getDamage() == 5);
        assertTrue(test.getSpeed() == 4.5);
        assertTrue(test.getHealth() == 20);

        test = new MobEnemy(MobType.Slow);
        assertTrue(test.getDamage() == 20);
        assertTrue(test.getSpeed() == 2);
        assertTrue(test.getHealth() == 100);

        test = new MobEnemy(MobType.Boss);
        assertTrue(test.getDamage() == 20);
        assertTrue(test.getSpeed() == 4);
        assertTrue(test.getHealth() == 1000);
    }

    @Test
    public void testStep() throws Exception {
        Player p = new Player(new Point(200,200));
        MobEnemy m;
        Point location;
        Point newlocation;

        m = new MobEnemy(MobType.Standard);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());

        m = new MobEnemy(MobType.Fast);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());

        m = new MobEnemy(MobType.Slow);
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 300 && newlocation.getY() < 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());
    }

    /**
     * Used for rounding movement distances in testStep
     * @param value
     * @param places
     * @return
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
