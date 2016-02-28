package GAME;

import java.util.Scanner;

/**
 * Created by dot on 25-02-2016.
 */
public class Input {
    /**
     * @return listens for keyboard input and returns it
     */
    public static String inGameGetKeyboardInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }
    public static String menuGetKeyboardInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }
}
