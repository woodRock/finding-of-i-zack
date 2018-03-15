package TheFindingOfIZack.Util;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.View.View;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Model;

public class CreateGameModel {

    public static Model newGame(View v ){
        Game g = new Game(new Player(new Point(GameDimensions.GAME_WIDTH /2 - 20, GameDimensions.GAME_HEIGHT/2 - 20)));
        g.addObserver(v);
        return g;
    }
}
