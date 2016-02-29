package GAME;

import GAME.Menu.Menu;
import GAME.Menu.MenuFactory;

public class Main {

    public static void main(String[] args) {

        StartUp startUp = new StartUp();
        startUp.startUp();
        Game game = null;

        MenuFactory menuFactory = new MenuFactory();

        Menu menu = menuFactory.getMenu("main", game);
        menu.Show();

    }
}
