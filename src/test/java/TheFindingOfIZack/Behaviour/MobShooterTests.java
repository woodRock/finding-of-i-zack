package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Player;

import TheFindingOfIZack.Util.Point;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by gordontheo on 16/10/17.
 */
public class MobShooterTests {
    @Test
    public void createNewShooter() {
        MobEnemy test = new MobEnemy(MobType.Shooter);
        assertTrue (test.getDamage() == 5);
        assertTrue (test.getSpeed() == 3);
        assertTrue (test.getHealth() == 50);
    }

    @Test
    public void testStep(){
        Player p = new Player(new Point(200,200));
        MobEnemy m = new MobEnemy(MobType.Shooter);
        Point location;
        Point newlocation;

        //Moving towards
        location = new Point(500,500);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() < 500 && newlocation.getY() < 500);
        assertTrue(round(Math.hypot(500 - newlocation.getX(), 500 - newlocation.getY()),2) == m.getSpeed());

        //Moving away
        location = new Point(300,300);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() > 300 && newlocation.getY() > 300);
        assertTrue(round(Math.hypot(300 - newlocation.getX(), 300 - newlocation.getY()),2) == m.getSpeed());

        //Buffer zone low
         int spawn = 342;
        location = new Point(spawn,spawn);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() == spawn && newlocation.getY() == spawn);

        //Buffer zone high
        spawn = 383;
        location = new Point(spawn,spawn);
        newlocation = m.step(location,p.getLocation(),p.getRoom());
        assertTrue(newlocation.getX() == spawn && newlocation.getY() == spawn);
    }

    @Test
    public void testNewProjectile(){
        Player p = new Player(new Point(200,200));
        MobShooter m = new MobShooter();
        Point location = new Point(350,350);

        for(int i=0;i<30;i++) {
            m.step(location, p.getLocation(), null);
        }
        assertTrue(location.getX() == 350 && location.getY() == 350);
        MobProjectile mp = m.getProjectile().get(0);
        assertTrue(m.getProjectile().size() == 1);
        assertTrue(mp.getLocation().getX() == 355 && mp.getLocation().getY() == 355);
        mp.move();
        assertTrue(round(Math.hypot(355 - mp.getLocation().getX(), 355 - mp.getLocation().getY()),2) == mp.getSpeed());
    }

    @Test
    public void testPopProjectileWall(){
        Player p = new Player(new Point(0,200));
        MobShooter m = new MobShooter();
        Point location = new Point(200,200);

        for(int i=0;i<30;i++) {
            m.step(location, p.getLocation(), null);
        }
        assertTrue(m.getProjectile().size() == 1);
        MobProjectile mp = m.getProjectile().get(0);
        for(int i=0;i<100;i++) {
            mp.move();
        }
        m.popProjectiles();
        assertTrue(m.getProjectile().isEmpty());
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
