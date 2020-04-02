package TheFindingOfIZack.Controller;

import TheFindingOfIZack.Mocks.MockModel;
import TheFindingOfIZack.Mocks.MockView;
import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {


    /**
     * Check that the display/view is told to show itself
     */
    @Test
    public void testShowGUICommunication(){
        MockView v = new MockView("Test View");
        GameController c = new GameController(v,new MockModel());

        c.showGUI();
        assertTrue("gui should be true",v.gui);
    }


    /**
     * Test that key events that correspond to player shooting controls perform actions
     */
    @Test
    public void testShootControls() throws Exception {
        MockView v = new MockView("Test View");
        MockModel m = new MockModel();
        GameController c = new GameController(v,m);

        /* Shoot up, simulate up key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP));
        assertTrue("Should have shoot up", m.sUp);
        assertFalse("Should only have shot up",m.sDown);
        assertFalse("Should only have shot up",m.sLeft);
        assertFalse("Should only have shot up",m.sRight);
        /* Shoot up, simulate up key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP));
        assertFalse("Should not be shooting", m.sUp);
        assertFalse("Should not be shooting",m.sDown);
        assertFalse("Should not be shooting",m.sLeft);
        assertFalse("Should not be shooting",m.sUp);

        /* Shoot down, simulate down key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN));
        assertFalse("Should have shot down", m.sUp);
        assertTrue("Should only have shot down",m.sDown);
        assertFalse("Should only have shot down",m.sLeft);
        assertFalse("Should only have shot down",m.sRight);
        /* Shoot down, simulate down key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN));
        assertFalse("Should not be shooting", m.sUp);
        assertFalse("Should not be shooting",m.sDown);
        assertFalse("Should not be shooting",m.sLeft);
        assertFalse("Should not be shooting",m.sUp);

        /* Shoot right, simulate right key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT));
        assertFalse("Should have shot right", m.sUp);
        assertFalse("Should only have shot right",m.sDown);
        assertFalse("Should only have shot right",m.sLeft);
        assertTrue("Should only have shot right",m.sRight);
        /* Shoot right, simulate right key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT));
        assertFalse("Should not be shooting", m.sUp);
        assertFalse("Should not be shooting",m.sDown);
        assertFalse("Should not be shooting",m.sLeft);
        assertFalse("Should not be shooting",m.sUp);

        /* Shoot left, simulate left key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT));
        assertFalse("Should have shot left", m.sUp);
        assertFalse("Should only have shot left",m.sDown);
        assertTrue("Should only have shot left",m.sLeft);
        assertFalse("Should only have shot left",m.sRight);
        /* Shoot left, simulate left key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT));
        assertFalse("Should not be shooting", m.sUp);
        assertFalse("Should not be shooting",m.sDown);
        assertFalse("Should not be shooting",m.sLeft);
        assertFalse("Should not be shooting",m.sUp);
    }

    /**
     * Test that key events that correspond to player movement controls perform actions
     */
    @Test
    public void testMoveControls() throws Exception {
        MockView v = new MockView("Test View");
        MockModel m = new MockModel();
        GameController c = new GameController(v,m);

        /* Move up, simulate up key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W));
        assertTrue("Should have moved up", m.mUp);
        assertFalse("Should only have moved up",m.mDown);
        assertFalse("Should only have moved up",m.mLeft);
        assertFalse("Should only have moved up",m.mRight);
        /* Move up, simulate up key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_W));
        assertFalse("Should not be moving", m.mUp);
        assertFalse("Should not be moving",m.mDown);
        assertFalse("Should not be moving",m.mLeft);
        assertFalse("Should not be moving",m.mRight);

        v = new MockView("Test View");
        m = new MockModel();
        c = new GameController(v,m);

        /* Move down, simulate down key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S));
        assertFalse("Should have moved down", m.mUp);
        assertTrue("Should only have moved down",m.mDown);
        assertFalse("Should only have moved down",m.mLeft);
        assertFalse("Should only have moved down",m.mRight);
        /* Move down, simulate down key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_S));
        assertFalse("Should not be moving", m.mUp);
        assertFalse("Should not be moving",m.mDown);
        assertFalse("Should not be moving",m.mLeft);
        assertFalse("Should not be moving",m.mRight);

        v = new MockView("Test View");
        m = new MockModel();
        c = new GameController(v,m);

        /* Move right, simulate right key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D));
        assertFalse("Should have moved right", m.mUp);
        assertFalse("Should only have moved right",m.mDown);
        assertFalse("Should only have moved right",m.mLeft);
        assertTrue("Should only have moved right",m.mRight);
        /* Move right, simulate right key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_D));
        assertFalse("Should not be moving", m.mUp);
        assertFalse("Should not be moving",m.mDown);
        assertFalse("Should not be moving",m.mLeft);
        assertFalse("Should not be moving",m.mRight);

        v = new MockView("Test View");
        m = new MockModel();
        c = new GameController(v,m);

        /* Move left, simulate left key press */
        c.keyPressed(new KeyEvent(v, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A));
        assertFalse("Should have moved left", m.mUp);
        assertFalse("Should only have moved left",m.mDown);
        assertTrue("Should only have moved left",m.mLeft);
        assertFalse("Should only have moved left",m.mRight);
        /* Move left, simulate left key release */
        c.keyReleased(new KeyEvent(v, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A));
        assertFalse("Should not be moving", m.mUp);
        assertFalse("Should not be moving",m.mDown);
        assertFalse("Should not be moving",m.mLeft);
        assertFalse("Should not be moving",m.mRight);
    }
}