package GAME.Menu;

import GAME.Game;

/**
 * Created by dot on 25-02-2016.
 */
public class MenuFactory {
    public Menu getMenu(String menu, Game game){
        switch (menu){
            case "main":
                return new MainMenu(this.getMenu("new", game), this.getMenu("save", game), this.getMenu("load", game), game);
            case "new":
                return new NewGameMenu();
            case "save":
                return new SaveGameMenu(game);
            case "load":
                return new LoadGameMenu();
        }
        return null;
    }
}