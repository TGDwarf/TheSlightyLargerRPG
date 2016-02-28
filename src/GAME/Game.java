package GAME;

import GAME.Menu.MenuFactory;
import GAME.Menu.Menu;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dot on 25-02-2016.
 */
public class Game {

    //TODO: BIG FUCKING TODO. Change draw map to retrieve list of creatures / player / corpses etc and add them to the map right before drawing, removing all the adding of monster to the map from the code
    private boolean inMapMenu = true;
    private static String lastCommand;
    int oldTexture = 0;

    public MapGenerator mapGenerator = new MapGenerator();
    MenuFactory menuFactory = new MenuFactory();
    ConsolePrinter consolePrinter = new ConsolePrinter();
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
            String playerCommand = Input.inGameGetKeyboardInput();
            if (playerCommand.equals("") && lastCommand != null || playerCommand.equals("w") || playerCommand.equals("a") || playerCommand.equals("s") || playerCommand.equals("d")){
                movePlayer(playerCommand);
            }
            if (playerCommand.equals("t") ){
                Menu menu = menuFactory.getMenu("main", "ingame");
                menu.Show();
            }
            if (playerCommand.equals("h") ){
                consolePrinter.printHelpMenu();
            }
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
                    playerLocationMapTextureUpdate(oldTexture);
                    player.getLocation().y --;
                    oldTexture = mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize];
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                break;

            case 's':
                destination.x = player.getLocation().x;
                destination.y = player.getLocation().y+1;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(oldTexture);
                    player.getLocation().y ++;
                    oldTexture = mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize];
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                break;

            case 'a':
                destination.x = player.getLocation().x-1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(oldTexture);
                    player.getLocation().x --;
                    oldTexture = mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize];
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                break;

            case 'd':
                destination.x = player.getLocation().x+1;
                destination.y = player.getLocation().y;
                if (isValidMove(destination)){
                    playerLocationMapTextureUpdate(oldTexture);
                    player.getLocation().x ++;
                    oldTexture = mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize];
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                break;
        }

    }

}
