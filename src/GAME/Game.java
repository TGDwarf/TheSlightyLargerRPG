package GAME;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by dot on 25-02-2016.
 */
public class Game {

    private boolean inMapMenu = true;
    private static String lastCommand;
    MapGenerator mapGenerator = new MapGenerator();
    Player player = new Player();

    public void startUp() {

        //consolePrinter.clearScreen();

        mapGenerator.Start();

        //mapGenerator.printRegionList();
        //mapGenerator.DrawWorldMap();
        player.setLocation(mapGenerator.playerStartingLocation);
        mapGenerator.DrawPlayerMap(player.getLocation());
        while(inMapMenu){
            String playerMove = Input.getKeyboardInput();
            movePlayer(playerMove);
        }

    }

    private boolean isValidMove(Point destination){
        if(mapGenerator.borderedMap[destination.x+mapGenerator.borderSize][destination.y+mapGenerator.borderSize] == 1){
            return false;
        }
        else{
            return true;
        }

    }

    private void movePlayer(String input)
    {
        Point destination = new Point();

        if (input.length() == 0) {
            input = lastCommand;
        }
        else if (input.toCharArray().length > 1) {
            return;
        }

        lastCommand = input;

        switch (input.toCharArray()[0])
        {
            case 'w':
                destination.x = player.getLocation().x;
                destination.y = player.getLocation().y-1;
                if (isValidMove(destination)){
                    player.getLocation().y --;
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(0, -1));
                break;

            case 's':
                destination.x = player.getLocation().x;
                destination.y = player.getLocation().y+1;
                if (isValidMove(destination)){
                    player.getLocation().y ++;
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(0, 1));
                break;

            case 'a':
                destination.x = player.getLocation().x-1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    player.getLocation().x --;
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(-1, 0));
                break;

            case 'd':
                destination.x = player.getLocation().x+1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    player.getLocation().x ++;
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(1, 0));
                break;
        }

    }

}
