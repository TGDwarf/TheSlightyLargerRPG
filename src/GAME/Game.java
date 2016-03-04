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
    private int oldTexture = 0;
    private int playerLevel = 1;
    private int turnsInGame = 0;
    private int creaturesDefeated = 0;

    private String playerName = "Default";

    public MapGenerator mapGenerator = new MapGenerator();
    MenuFactory menuFactory = new MenuFactory();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    Player player = new Player(playerName, "the player", playerLevel, new Damage(WeaponTypes.Hands) );
    Input input = new Input();
    Creature creature;
    List<Creature> creatures = new ArrayList<>();
    LeaderboardSQL leaderboardSQL = new LeaderboardSQL();

    public void startUp() {

        //consolePrinter.clearScreen();

        mapGenerator.Start();

        //mapGenerator.printRegionList();
        mapGenerator.DrawWorldMap();
        player.setLocation(mapGenerator.playerStartingLocation);
        spawnCreatures();

        leaderboardSQL.insertIntoLeaderBoard(this);
        mapGenerator.DrawPlayerMap(player.getLocation());
        consolePrinter.printMapInfo();
        while(inMapMenu){
            //TODO: Insert creature move code call here
            //TODO: Insert code to add X creatures every time player has defeated X creatures
            String playerCommand = input.inGameGetKeyboardInput();
            if (playerCommand.equals("") && lastCommand != null || playerCommand.equals("w") || playerCommand.equals("a") || playerCommand.equals("s") || playerCommand.equals("d")){
                movePlayer(playerCommand);
            }
            if (playerCommand.equals("t") ){
                Menu menu = menuFactory.getMenu("main", this);
                menu.Show();
                mapGenerator.DrawPlayerMap(player.getLocation());
            }
            if (playerCommand.equals("h") ){
                consolePrinter.printHelpMenu();
            }
            if (playerCommand.equals("m") ){
                consolePrinter.printMapInfo();
            }
            if (playerCommand.equals("u") ){
                player.setExperience(player.getExperience()+50);
                consolePrinter.print("You cheated! Good on you.");
            }
            if (playerCommand.equals("p") ){
                XMLHandler xmlHandler = new XMLHandler();
                xmlHandler.XMLWriter(this);
            }
            if (playerCommand.equals("r") ){
                XMLHandler xmlHandler = new XMLHandler();
                xmlHandler.XMLReader();
            }
            if (playerCommand.equals("l") ){
                leaderboardSQL.getLeaderboard();
            }

            player.endturnHeal();
            if (player.getLevel() != (int)player.getExperience()/50+1){
                player.setLevel((int)player.getExperience()/50+1);
                player.setHealth_Max(player.calculateMaxHealth());
            }

            turnsInGame++;
            //TODO: Update highscore on sql server, Playername + level + Experience + turns + creatures killed
        }

    }

    private void spawnCreatures(){
        for (Point spawnPoint : mapGenerator.creatureSpawnLocations) {
            creature = new Creature("Troll", "A hideous being with bad breath", playerLevel, new Damage(WeaponTypes.Hands));
            creature.setLocation(spawnPoint);
            mapGenerator.borderedMap[creature.getLocation().x+mapGenerator.borderSize][creature.getLocation().y+mapGenerator.borderSize] = 3;
            creatures.add(creature);
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

    private void checkForCombat(Point destination){
        for (Creature creature : creatures) {
            if (creature.getLocation().equals(destination)) {
                if (creature.isAlive()){
                    Combat combat = new Combat(player, creature);
                    if (combat.getWinner().equals(player)){
                        player.setExperience(player.getExperience() + creature.getHealth_Max());
                        creaturesDefeated++;
                        mapGenerator.borderedMap[creature.getLocation().x+mapGenerator.borderSize][creature.getLocation().y+mapGenerator.borderSize] = 2;
                        leaderboardSQL.updateLeaderBoard(this);
                    }
                    else {
                        consolePrinter.gameoverScreen();
                        leaderboardSQL.updateLeaderBoard(this);
                        System.exit(0);
                    }
                }
            }
        }
    }

    private void movePlayer(String playerInput)
    {
        Point destination = new Point();

        if (playerInput.length() == 0) {
            playerInput = lastCommand;
        }
        else if (playerInput.toCharArray().length > 1) {
            return;
        }

        lastCommand = playerInput;

        switch (playerInput.toCharArray()[0])
        {
            case 'w':
                destination.x = player.getLocation().x;
                destination.y = player.getLocation().y-1;
                if (isValidMove(destination)){
                    checkForCombat(destination);
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
                    checkForCombat(destination);
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
                    checkForCombat(destination);
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
                    checkForCombat(destination);
                    playerLocationMapTextureUpdate(oldTexture);
                    player.getLocation().x ++;
                    oldTexture = mapGenerator.borderedMap[player.getLocation().x+mapGenerator.borderSize][player.getLocation().y+mapGenerator.borderSize];
                    playerLocationMapTextureUpdate(5);
                }
                mapGenerator.DrawPlayerMap(player.getLocation());
                break;
        }

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTurnsInGame() {
        return turnsInGame;
    }

    public int getCreaturesDefeated() {
        return creaturesDefeated;
    }

}
