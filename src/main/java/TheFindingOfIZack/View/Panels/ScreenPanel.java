package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameDimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Abstract representation of the main panel to form the game GUI
 * Can have it's own panels to form a more complex layout
 *
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public abstract class ScreenPanel extends JPanel {

    /**
     * Constructor setting default background colour for all ScreenPanels
     */
    ScreenPanel(){
        super();
        this.setBackground(Color.BLACK);
    }

    /**
     * Allow for Button interactions to be delegated to a ActionListener elsewhere
     * @param controller ActionListener to associate any button interactions with
     */
    public abstract void addControllerForButtons(ActionListener controller);

    /**
     * Enable any buttons that may have been disabled
     */
    public abstract void enableOtherButtons();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GameDimensions.WINDOW_WIDTH, GameDimensions.WINDOW_HEIGHT);
    }

    /**
     * Disable buttons as indicated by implementation
     */
    public abstract void disableOtherButtons();

    /**
     * Change the primary text element
     */
    public void changeText(String[] text){}


}
