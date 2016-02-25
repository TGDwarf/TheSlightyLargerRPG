package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class Game {

    private boolean inStartMenu = true;
    MapGenerator mapGenerator = new MapGenerator();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    MapGenerator.Coord playerLocation;

    public void startUp() {


        consolePrinter.printLogo();

        consolePrinter.printStartInfo();

        consolePrinter.clearScreen();

        mapGenerator.Start();



        //mapGenerator.printRegionList();
        //mapGenerator.DrawWorldMap();
        mapGenerator.DrawPlayerMap(mapGenerator.playerStartingLocation);
        playerLocation = mapGenerator.playerStartingLocation;
        while(inStartMenu){
            String playerMove = Input.getKeyboardInput();
            movePlayer(playerMove);
        }

    }

    /**
     * A java version of struct containing simple Coord information
     */
    final class Coord {
        public int tileX;
        public int tileY;

        public Coord(int x, int y) {
            tileX = x;
            tileY = y;
        }
    }

    private void movePlayer(String input)
    {
        if (input.toCharArray().length == 1)
        {
            switch (input.toCharArray()[0])
            {
                case 'w':
                    playerLocation.tileY ++;
                    mapGenerator.DrawPlayerMap(playerLocation);
                    //movePlayer(hero, new Point(0, -1));
                    break;

                case 's':
                    playerLocation.tileY --;
                    mapGenerator.DrawPlayerMap(playerLocation);
                    //movePlayer(hero, new Point(0, 1));
                    break;

                case 'a':
                    playerLocation.tileX --;
                    mapGenerator.DrawPlayerMap(playerLocation);
                    //movePlayer(hero, new Point(-1, 0));
                    break;

                case 'd':
                    playerLocation.tileX ++;
                    mapGenerator.DrawPlayerMap(playerLocation);
                    //movePlayer(hero, new Point(1, 0));
                    break;
            }
        }
    }

}
