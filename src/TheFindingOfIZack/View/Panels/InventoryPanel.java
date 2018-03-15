package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Panel used to to display player and game stats and inventory
 * Displays -
 *  Health
 *  Armour
 *  Presence of Key in inventory
 *
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public class InventoryPanel extends JPanel{

    private static final int PADDING = 35;
    private static final int BAR_PADDING = 20;
    private static final int BAR_HEIGHT = 20;
    private static final int ARC = 20;

    private static final int BAR_WIDTH = 100;

    /* Images used to customise GUI */
    private static final Image header;
    private static final Image healthImg;
    private static final Image armourImg;
    private static final Image keyImg;
    private Model model;

    static {
        header = ImageLoader.loadImage("/header.png").getScaledInstance(GameDimensions.GAME_WIDTH, GameDimensions.MENU_HEIGHT,Image.SCALE_DEFAULT);
        healthImg = ImageLoader.loadImage("/potion.png");
        keyImg = ImageLoader.loadImage("/doorKey.png");
        armourImg = ImageLoader.loadImage("/armourIcon.png");
    }

    /**
     * Constructor for a InventoryPanel, requires the model it is representing
     * @param m Model that this panel is representing
     */
    public InventoryPanel(Model m){
        super();
        this.model = m;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawHealth(g);
        drawPlayerArmour(g);
        drawPlayerItems(g);
    }

    /**
     * Draw the players health bar
     * @param g Graphic object to draw on
     */
    private void drawHealth(Graphics g){
        double maxHealth = model.getPlayer().getMaxHealth();
        double health = Math.max(model.getPlayer().getHealth(),0);

        int healthBar = (int) (health / maxHealth * BAR_WIDTH);

        g.drawImage(header,0,0,null);
        g.setColor(Color.GRAY);
        g.drawImage(healthImg,PADDING,BAR_PADDING,null);
        g.fillRoundRect(PADDING*3, BAR_PADDING * 2, BAR_WIDTH,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.red);
        g.fillRoundRect(PADDING*3, BAR_PADDING * 2, healthBar,BAR_HEIGHT,ARC,ARC);
    }

    /**
     * Draw the players inventory
     * Currently just indicates presence of key
     * @param g Graphic object to draw on
     */
    private void drawPlayerItems(Graphics g){
        if (model.getPlayer().getKey()){
            g.drawImage(keyImg, GameDimensions.GAME_WIDTH - PADDING*4,BAR_PADDING * 2,null);
        }
    }

    /**
     * Draw the player armour bar
     * @param g Graphic object to draw on
     */
    private void drawPlayerArmour(Graphics g){
        double maxArmour = model.getPlayer().getMaxArmour() * 4;
        double armour = Math.max(model.getPlayer().getArmour(),0) * 4;

        int armourBar = (int) (armour / maxArmour * BAR_WIDTH);

        g.setColor(Color.GRAY);
        g.drawImage(armourImg, PADDING * 4 + BAR_WIDTH,BAR_PADDING,null);
        g.fillRoundRect(PADDING * 6 + BAR_WIDTH, BAR_PADDING *2 , BAR_WIDTH,BAR_HEIGHT,ARC,ARC);
        g.setColor(Color.blue);
        g.fillRoundRect(PADDING * 6 + BAR_WIDTH, BAR_PADDING *2, armourBar,BAR_HEIGHT,ARC,ARC);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GameDimensions.GAME_WIDTH, GameDimensions.MENU_HEIGHT);
    }
}
