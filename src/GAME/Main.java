package GAME;

import GAME.Menu.Menu;
import GAME.Menu.MenuFactory;

public class Main {


    public static void main(String[] args) {
        MenuFactory menuFactory = new MenuFactory();


        Menu menu = menuFactory.getMenu("main");
        menu.Show();

    }
}
