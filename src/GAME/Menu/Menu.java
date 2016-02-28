package GAME.Menu;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dot on 25-02-2016.
 */
public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<MenuItem>();

    private class MenuItem {
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



    public boolean Add(String text, MenuCallback menuCallback) {
        return items.add(new MenuItem(text, menuCallback));
    }

    public void Show() {
        int chosen = 0;
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < items.size(); ++i) {
            MenuItem menuItem = items.get(i);
            System.out.printf("[%d] %s \n", i + 1, menuItem.getText());
        }

        System.out.println();

        try {
            chosen = in.nextInt();
        } catch (Exception ex) { }

        if (chosen > items.size() || chosen < 1) {
            System.out.println("Invalid option.\nPress enter to continue...");
            in.nextLine();
            in.nextLine();
        } else {
            MenuItem menuItem = items.get(chosen - 1);
            MenuCallback menuCallback = menuItem.getMenuCallback();
            menuCallback.Invoke();
        }
    }
}
