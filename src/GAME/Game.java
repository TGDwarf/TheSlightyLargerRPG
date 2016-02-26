package GAME;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dot on 25-02-2016.
 */
public class Game {

    private boolean inMapMenu = true;
    private static String lastCommand;

    public MapGenerator getMapGenerator() {
        return mapGenerator;
    }

    MapGenerator mapGenerator = new MapGenerator();
    Player player = new Player();
    Creature creature;
    List<Creature> creatures = new ArrayList<>();

    public void startUp() {

        //consolePrinter.clearScreen();

        mapGenerator.Start();

        //mapGenerator.printRegionList();
        //mapGenerator.DrawWorldMap();
        player.setLocation(mapGenerator.playerStartingLocation);
        for (Point spawnPoint : mapGenerator.creatureSpawnLocations) {
            creature = new Creature();
            creature.setLocation(spawnPoint);
            mapGenerator.borderedMap[creature.getLocation().x+mapGenerator.borderSize][creature.getLocation().y+mapGenerator.borderSize] = 3;
            creatures.add(creature);
        }

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

    void playerLocationMapTextureUpdate(int texture){
        mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize] = texture;
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
                    playerLocationMapTextureUpdate(0);
                    player.getLocation().y --;
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(0, -1));
                break;

            case 's':
                destination.x = player.getLocation().x;
                destination.y = player.getLocation().y+1;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(0);
                    player.getLocation().y ++;
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(0, 1));
                break;

            case 'a':
                destination.x = player.getLocation().x-1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(0);
                    player.getLocation().x --;
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(-1, 0));
                break;

            case 'd':
                destination.x = player.getLocation().x+1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(0);
                    player.getLocation().x ++;
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                //movePlayer(hero, new Point(1, 0));
                break;
        }

    }

}
