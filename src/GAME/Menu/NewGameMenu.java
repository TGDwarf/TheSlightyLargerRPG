package GAME.Menu;

import GAME.ConsolePrinter;
import GAME.Game;
import GAME.Input;

/**
 * Created by dot on 25-02-2016.
 */
public class NewGameMenu extends Menu {

    Input input = new Input();
    Game game = new Game();
    int gameWidth = game.mapGenerator.getWidth();
    int gameHeight = game.mapGenerator.getHeight();
    int gamePlayerViewField = game.mapGenerator.getBorderSize();
    int gameMonsterDensity = game.mapGenerator.getMonsterDensity();
    String gameSeed = game.mapGenerator.getSeed();
    boolean gameSeedRandom = game.mapGenerator.getUseRandomSeed();

    private boolean settingsAccepted = false;

    @Override
    public void Show() {
        while (!settingsAccepted) {
            int chosen = 0;
            for (int i = 0; i < getItems().size(); ++i) {
                MenuItem menuItem = getItems().get(i);
                System.out.printf("[%d] %s \n", i + 1, menuItem.getText());
            }

            System.out.println();

            chosen = input.menuGetIntKeyboardInput();

            if (chosen > getItems().size() || chosen < 1) {
                System.out.println("Invalid option.\nPress enter to continue...");
                input.menuGetKeyboardInput();
                input.menuGetKeyboardInput();
            } else {
                MenuItem menuItem = getItems().get(chosen - 1);
                MenuCallback menuCallback = menuItem.getMenuCallback();
                menuCallback.Invoke();
            }
        }
    }

    public NewGameMenu() {
        this.Add("Accept settings and start game", new MenuCallback() {
            public void Invoke() {

                game.startUp();
            }
        });
        this.Add("Please enter a player name - Default Name = " + game.getPlayerName(), new MenuCallback() {
            public void Invoke() {
                try {
                    game.setPlayerName(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                //TODO: Call up the menu again
            }
        });
        this.Add("Map Width - Default width = " + gameWidth, new MenuCallback() {
            public void Invoke() {
                try {
                    gameWidth = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setWidth(gameWidth);
                //TODO: Call up the menu again
            }
        });
        this.Add("Map Height - Default Height = " + gameHeight, new MenuCallback() {
            public void Invoke() {
                try {
                    gameHeight = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setWidth(gameWidth);
                //TODO: Call up the menu again
            }
        });
        this.Add("Player View Distance (Recommend 3-15) - Default = " + gamePlayerViewField, new MenuCallback() {
            public void Invoke() {
                try {
                    gamePlayerViewField = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setBorderSize(gamePlayerViewField);
                game.mapGenerator.setPlayerViewField(gamePlayerViewField);
                //TODO: Call up the menu again
            }
        });
        this.Add("Monster density (Recommend 1-10) - Default = " + gameMonsterDensity, new MenuCallback() {
            public void Invoke() {
                try {
                    gameMonsterDensity = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setMonsterDensity(gameMonsterDensity);
                //TODO: Call up the menu again
            }
        });
        this.Add("Map seed - Default = " + gameSeed, new MenuCallback() {
            public void Invoke() {
                gameSeed = input.menuGetKeyboardInput();
                game.mapGenerator.setSeed(gameSeed);
                //TODO: Call up the menu again
            }
        });
        this.Add("Use random game seed, enter true or false - Default = " + gameSeedRandom, new MenuCallback() {
            public void Invoke() {
                try {
                    if (input.menuGetKeyboardInput() == "true") {
                        gameSeedRandom = true;
                    }
                    if (input.menuGetKeyboardInput() == "false"){
                        gameSeedRandom = false;
                    }

                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setUseRandomSeed(gameSeedRandom);
                //TODO: Call up the menu again
            }
        });
    }
}
