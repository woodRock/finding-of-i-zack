package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The display intended for first landing
 * Has options for the user to:
 *  Start a new game
 *  Load a game
 *  or exit
 *
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public class StartScreenPanel extends ScreenPanel {
    /* Buttons displayed on StartScreenPanel */
    private final JButton newGame;
    private final JButton loadGame;
    private final JButton saveGame;
    private final JButton resumeGame;
    private final JButton exitGame;

    /* Images used to customise buttons */
    private static final Icon normalButton;
    private static final Icon disabledButton;
    private static final Icon hoverButton;
    private static final Image startBG;
    private static final Image controls;

    static {
        normalButton = ImageLoader.loadIcon("/normalButton.jpg");
        disabledButton = ImageLoader.loadIcon("/disabledButton.jpg");
        hoverButton = ImageLoader.loadIcon("/hoverButton.jpg");

        startBG = ImageLoader.loadImage("/startBG.jpg").getScaledInstance(GameDimensions.WINDOW_WIDTH, GameDimensions.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
        controls = ImageLoader.loadImage("/keys.png").getScaledInstance(330,150,Image.SCALE_DEFAULT);
    }

    /**
     * Create the panel and related buttons
     */
    public StartScreenPanel(){
        super();
        this.newGame = buildButton("New Game", "newGame");
        this.add(newGame);

        this.loadGame = buildButton("Load", "loadGame");
        this.add(loadGame);

        this.saveGame = buildButton("Save","saveGame");
        this.saveGame.setEnabled(false);    //Initially disabled
        this.add(saveGame);

        this.resumeGame = buildButton("Resume","resumeGame");
        this.resumeGame.setEnabled(false);  //Initially disabled
        this.add(resumeGame);

        this.exitGame = buildButton("Exit","exitGame");
        this.add(exitGame);

        this.setVisible(true);
    }

    /**
     * Create a button with a given name and command
     * Allows for consistent button appearance
     * @param name  String displayed on the button
     * @param command   String action command when button is used
     * @return  The button created
     */
    private JButton buildButton(String name,String command){

        JButton b = new JButton(name,normalButton);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.CENTER);
        b.setDisabledIcon(disabledButton);
        b.setRolloverIcon(hoverButton);
        b.setForeground(Color.lightGray);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setActionCommand(command);

        return b;
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {
        /* Give ActionListener to all buttons */
        newGame.addActionListener(controller);
        loadGame.addActionListener(controller);
        saveGame.addActionListener(controller);
        resumeGame.addActionListener(controller);
        exitGame.addActionListener(controller);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(startBG, 0, 0, null);
        g.drawImage(controls, GameDimensions.WINDOW_WIDTH/2 - 330/2, GameDimensions.WINDOW_HEIGHT / 3 * 2,null);
    }
    @Override
    public void enableOtherButtons(){
        this.resumeGame.setEnabled(true);
        this.saveGame.setEnabled(true);
    }

    @Override
    public void disableOtherButtons() {
        saveGame.setEnabled(false);
        resumeGame.setEnabled(false);
    }
}
