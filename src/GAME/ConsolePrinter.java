package GAME;

import java.util.Map;

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
        for (int i = 0; i < 30; i++)
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

    public void printMap(int mapTile) {
        switch (mapTile){
            case 0:
                System.out.print(IMapTextures.floorTexture);
                break;
            case 1:
                System.out.print(IMapTextures.wallTexture);
                break;
            case 3:
                System.out.print(IMapTextures.monsterTexture);
                break;
            case 5:
                System.out.print(IMapTextures.playerTexture);
                break;
            case 7:
                System.out.print(IMapTextures.fightTexture);
                break;

        }


    }
}
