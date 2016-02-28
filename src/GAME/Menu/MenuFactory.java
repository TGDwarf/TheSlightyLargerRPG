package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class MenuFactory {
    public Menu getMenu(String menu, String type){
        switch (menu){
            case "main":
                return new MainMenu(this.getMenu("new", type), this.getMenu("save", type), this.getMenu("load", type), type);
            case "new":
                return new NewGameMenu();
            case "save":
                return new SaveGameMenu();
            case "load":
                return new LoadGameMenu();
            case "battle":
                return new BattleMenu();
        }
        return null;
    }
}