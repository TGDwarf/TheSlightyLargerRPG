package GAME.Menu;

import GAME.Game;

/**
 * Created by dot on 25-02-2016.
 */
public class MainMenu extends Menu {
    public MainMenu(Menu newGameMenu, Menu saveGameMenu, Menu loadGameMenu, Game game) {
        if (game != null){
            this.Add("Back", new MenuCallback() {
                public void Invoke() {
                    return;
                }
            });
            this.Add("New Game", new MenuCallback() {
                public void Invoke() {
                    newGameMenu.Show();
                }
            });
            this.Add("Save Game", new MenuCallback() {
                public void Invoke() {
                    saveGameMenu.Show();
                }
            });
            this.Add("Load Game", new MenuCallback() {
                public void Invoke() {
                    loadGameMenu.Show();
                }
            });
            this.Add("Quit Game", new MenuCallback() {
                public void Invoke() {
                    System.exit(0);
                }
            });
        }
        else{
            this.Add("New Game", new MenuCallback() {
                public void Invoke() {
                    newGameMenu.Show();
                }
            });
            this.Add("Load Game", new MenuCallback() {
                public void Invoke() {
                    loadGameMenu.Show();
                }
            });
            this.Add("Quit Game", new MenuCallback() {
                public void Invoke() {
                    System.exit(0);
                }
            });
        }

    }
}
