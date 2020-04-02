package TheFindingOfIZack.View;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.View.Panels.GameEndScreen;
import TheFindingOfIZack.View.Panels.GamePanel;
import TheFindingOfIZack.View.Panels.ScreenPanel;
import TheFindingOfIZack.View.Panels.StartScreenPanel;
import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Manage the view for the game.
 * Handles what needs to be drawn and the GUI
 *
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public class ViewManager extends View {

    private Model model;

    private ScreenPanel startScreen;
    private ScreenPanel gameScreen;
    private ScreenPanel endScreen;

    /**
     * Initialise the ViewManager.
     * Takes a controller as an argument in order to create key bindings
     */
    public ViewManager(Model model) {
        super("The Finding of I, Zack");
        /* Set the default font for all UI elements */
        setUIFont (new javax.swing.plaf.FontUIResource(new Font("ComicSans", Font.BOLD, 18)));
        setPreferredSize(new Dimension(GameDimensions.WINDOW_WIDTH, GameDimensions.WINDOW_HEIGHT));

        this.model = model;
        this.setFocusable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.startScreen = new StartScreenPanel();
        this.gameScreen = new GamePanel(this.model);

        this.endScreen = new GameEndScreen(new String[]{"Game Over"});

        this.add(this.startScreen);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void update(Observable observable, Object o) {
        /* Check if game is won or lost */
        if (model.isGameLost()){
            endScreen.changeText(new String[]{"YOU APPEAR TO HAVE DIED","GAME OVER"});
            goToEndView();
        }else if (model.isGameWon()){
            endScreen.changeText(new String[]{"CONGRATULATIONS","YOU FOUND ZACK!","IT'S YOU!"});
            goToEndView();
        }
        repaint();
    }

    @Override
    public void showGUI() {
        this.setVisible(true);
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {
        this.startScreen.addControllerForButtons(controller);
        this.gameScreen.addControllerForButtons(controller);
        this.endScreen.addControllerForButtons(controller);
    }



    @Override
    public void goToGameView() {
        this.remove(startScreen);
        this.add(gameScreen);
        this.remove(endScreen);
        this.repaint();
    }

    @Override
    public void goToMenuView() {
        this.remove(gameScreen);
        this.remove(endScreen);
        this.add(startScreen);
        this.repaint();
    }

    @Override
    public void repaint(){
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @Override
    public void newGame(Model game) {
        this.model = game;
        this.gameScreen = new GamePanel(this.model);
    }

    @Override
    public void enableOtherButtons() {
        this.startScreen.enableOtherButtons();
        this.gameScreen.enableOtherButtons();
    }

    @Override
    public void disableOtherButtons() {
        this.startScreen.disableOtherButtons();
        this.gameScreen.disableOtherButtons();
        this.endScreen.disableOtherButtons();
    }

    @Override
    public void goToEndView(){
        this.remove(gameScreen);
        this.remove(startScreen);
        this.add(endScreen);
        this.repaint();
    }

    /**
     * Set a global font for all view elements
     * @param f the font to use
     */
    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }
}