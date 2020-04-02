package TheFindingOfIZack.View;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * An abstract implementation of what a View needs to provide
 *
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public abstract class View extends JFrame implements Observer {

    public View(String name){
        super(name);
    }

    /**
     * Allow the GUI to be shown at a later stage
     */
    public abstract void showGUI();

    /**
     * Link controllers that manage actions on buttons presses to be linked to the view and sub views
     * @param controller controller being added
     */
    public abstract void addControllerForButtons(ActionListener controller);

    /**
     * Allow the view to be changed to only display game information and items
     */
    public abstract void goToGameView();

    /**
     * Allow the view to be changed to only display menu information and items
     */
    public abstract void goToMenuView();

    /**
     * Replace references to the Model with a new model
     * Useful when loading a saved game
     * @param model Model that should now be referenced
     */
    public abstract void newGame(Model model);

    /**
     * Enable buttons that may be initially disabled
     * e.g. activate save button only after a game has been created / loaded
     */
    public abstract void enableOtherButtons();

    /**
     *
     */
    public abstract void goToEndView();

    /**
     * Disable buttons that may need to be disabled
     */
    public abstract void disableOtherButtons();
}