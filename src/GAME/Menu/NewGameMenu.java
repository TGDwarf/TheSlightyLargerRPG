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

    public NewGameMenu() {
        this.Add("Accept settings and start game", new MenuCallback() {
            public void Invoke() {

                game.startUp();
            }
        });
        this.Add("Map Width - Current width = " + gameWidth, new MenuCallback() {
            public void Invoke() {
                try {
                    gameWidth = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setWidth(gameWidth);
                //TODO: Call up the menu again
            }
        });
        this.Add("Map Height - Current Height = " + gameHeight, new MenuCallback() {
            public void Invoke() {
                try {
                    gameHeight = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setWidth(gameWidth);
                //TODO: Call up the menu again
            }
        });
        this.Add("Player View Distance (Recommend 3-15) - Current = " + gamePlayerViewField, new MenuCallback() {
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
        this.Add("Monster density (Recommend 1-10) - Current = " + gameMonsterDensity, new MenuCallback() {
            public void Invoke() {
                try {
                    gameMonsterDensity = Integer.parseInt(input.menuGetKeyboardInput());
                } catch (NumberFormatException e) {

                }
                game.mapGenerator.setMonsterDensity(gameMonsterDensity);
                //TODO: Call up the menu again
            }
        });
        this.Add("Map seed - Current = " + gameSeed, new MenuCallback() {
            public void Invoke() {
                gameSeed = input.menuGetKeyboardInput();
                game.mapGenerator.setSeed(gameSeed);
                //TODO: Call up the menu again
            }
        });
        this.Add("Use random game seed, enter true or false - Current = " + gameSeedRandom, new MenuCallback() {
            public void Invoke() {
                try {
                    if (Input.menuGetKeyboardInput() == "true") {
                        gameSeedRandom = true;
                    }
                    if (Input.menuGetKeyboardInput() == "false"){
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
