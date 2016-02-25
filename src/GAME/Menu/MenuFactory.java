package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class MenuFactory {
    public Menu getMenu(String menu){
        switch (menu){
            case "main":
                return new MainMenu(this.getMenu("new"), this.getMenu("save"), this.getMenu("load"));
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