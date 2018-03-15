package TheFindingOfIZack;

import TheFindingOfIZack.Controller.GameController;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;

public class Main {
    public static void main(String[] args){

        Player player = new Player(new Point(GameDimensions.GAME_WIDTH /2 - 20, GameDimensions.GAME_HEIGHT/2 - 20));
        Game game = new Game(player);
        ViewManager view = new ViewManager(game);
        game.addObserver(view);

        GameController controller = new GameController(view,game);
        controller.showGUI();

    }
}
