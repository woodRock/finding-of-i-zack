package TheFindingOfIZack.View.Panels;


import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static TheFindingOfIZack.Util.GameDimensions.*;

/**
 * Panel indented to display the game
 * Consists of other panels to structure layout
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public class GamePanel extends ScreenPanel {

    private Model model;
    private JPanel topInfo;
    private JPanel gameArea;

    /**
     * Create a panel intended to draw the game on
     * @param model reference to the model being drawn
     */
    public GamePanel(Model model){
        super();
        this.model = model;
        this.topInfo = new InventoryPanel(this.model);
        this.topInfo.setPreferredSize(new Dimension(GAME_WIDTH,MENU_HEIGHT));
        this.gameArea = new GameArea(this.model);
        this.gameArea.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));

        this.add(topInfo,BorderLayout.NORTH);
        this.add(gameArea,BorderLayout.SOUTH);
    }

    @Override
    public void addControllerForButtons(ActionListener controller) {}

    @Override
    public void enableOtherButtons() {}

    @Override
    public void disableOtherButtons() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /*
         * Draw player information such as health, armour, items etc
         */
        this.topInfo.repaint();
        this.gameArea.repaint();
    }
}
