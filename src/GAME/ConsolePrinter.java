package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class ConsolePrinter {

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    /**
     * Clears the screen, for loop should be outcommented before release
     */
    public void clearScreen(){
        for (int i = 0; i < 25; i++)
            System.out.println();
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) { }
    }

    /**
     * Name says it all really, prints the logo.
     */
    public void printLogo(){
        // Title Screen
        System.out.println("+-------------------------------+");
        System.out.println("| TLRPG: The Bigger Text RPG    |");
        System.out.println("|            (\\_/)              |");
        System.out.println("|           (='.'=)             |");
        System.out.println("|          (\\\")_(\\\")            |");
        System.out.println("+-------------------------------+");
        System.out.println();
    }

    /**
     * Very sparse start up, could be used for MOTD etc.
     */
    public void printStartInfo() {
        System.out.println("Welcome to the game");
        System.out.println();
        System.out.println("Here are your options:");
    }
}
