package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class MenuFactory {
    public Menu getMenu(String menu){
        switch (menu){
            case "main":
                return new MainMenu();
            case "save":
                System.out.println("1");
                break;
            case "load":
                System.out.println("1");
                break;
            case "battle":
                System.out.println("1");
                break;
        }
        return null;
    }
}