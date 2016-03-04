package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class StartUp {
    ConsolePrinter consolePrinter = new ConsolePrinter();

    /**
     * Just prints stuff really
     */
    public void startUp(){
        consolePrinter.printLogo();

        consolePrinter.printStartInfo();

    }
}
