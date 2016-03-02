package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class ConsolePrinter {

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    /**
     * Clears the screen, for loop should be out-commented before release
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

    /**
     * prints a map texture depending on input
     * @param mapTile int to Map Texture
     */
    public void printMap(int mapTile) {
        switch (mapTile){
            case 0:
                System.out.print(IMapTextures.floorTexture);
                break;
            case 1:
                System.out.print(IMapTextures.wallTexture);
                break;
            case 2:
                System.out.print(IMapTextures.corpseTexture);
                break;
            case 3:
                System.out.print(IMapTextures.monsterTexture);
                break;
            case 4:
                System.out.print(IMapTextures.vendorTexture);
                break;
            case 5:
                System.out.print(IMapTextures.playerTexture);
                break;
            case 7:
                System.out.print(IMapTextures.fightTexture);
                break;

        }


    }

    /**
     * Prints the help menu
     */
    public void printHelpMenu() {
        System.out.println("Here are your options while in game traveling the map");
        System.out.println("Type w / a / s / d to move in a direction");
        System.out.println("To move in the same direction again simply hit enter");
        System.out.println("Type t to enter the main menu again");
        System.out.println("Type h to show this menu again");
        System.out.println("Type m to show this map info");
    }

    public void gameoverScreen(){
        // Failure Screen
        System.out.println("The game is over, you lost... maybe try again?");
        System.out.println();
    }

    public void printMapInfo(){
        System.out.println("floorTexture = \" \"");
        System.out.println("wallTexture = \"X\"");
        System.out.println("monsterTexture = \"M\"");
        System.out.println("monsterTexture = \"C\"");
        System.out.println("vendorTexture = \"V\"");
        System.out.println("playerTexture = \"P\"");
        System.out.println("fightTexture = \"F\"");
    }
}
