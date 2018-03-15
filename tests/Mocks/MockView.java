package Mocks;

import TheFindingOfIZack.View.View;
import TheFindingOfIZack.World.Model;

import java.awt.event.ActionListener;
import java.util.Observable;

public class MockView extends View{
    public boolean gui = false;
    public boolean gameView = false;
    public boolean menuView = false;

    public MockView(String name) {
        super(name);
        setFocusable(true);
        requestFocus();
    }

    public void showGUI() {
        gui = true;
    }


    public void addControllerForButtons(ActionListener controller) {

    }


    public void goToGameView() {
        gameView = true;
        menuView = false;
    }


    public void goToMenuView() {
        menuView = true;
        gameView = false;
    }

    @Override
    public void newGame(Model model) {

    }

    @Override
    public void enableOtherButtons() {

    }

    @Override
    public void goToEndView() {

    }

    @Override
    public void disableOtherButtons() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
