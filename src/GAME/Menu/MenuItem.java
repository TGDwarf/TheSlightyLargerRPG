package GAME.Menu;

/**
 * Created by dot on 29-02-2016.
 */
public class MenuItem {
    private MenuCallback menuCallback;
    private String text;


    public MenuItem(String text, MenuCallback menuCallback) {
        this.menuCallback = menuCallback;
        this.text = text;
    }

    public MenuCallback getMenuCallback() {
        return menuCallback;
    }

    public String getText() {
        return text;
    }
}
