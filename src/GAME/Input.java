package GAME;

import java.util.Scanner;

/**
 * Created by dot on 25-02-2016.
 */
public class Input {
    public static String getKeyboardInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }
}
