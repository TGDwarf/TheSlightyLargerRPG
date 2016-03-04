package GAME;

import java.util.Scanner;

/**
 * Created by dot on 25-02-2016.
 */
public class Input {
    /**
     * @return listens for keyboard input and returns it, used when no menu is shown
     */
    public String inGameGetKeyboardInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }

    /**
     * @return listens for keyboard input and returns it, used in menus
     */
    public String menuGetKeyboardInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }

    /**
     * @return listens for keyboard input and returns it, used in menus
     */
    public int menuGetIntKeyboardInput() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        try {
            input = in.nextInt();
        } catch (Exception ex) { }
        return input;
    }
}
