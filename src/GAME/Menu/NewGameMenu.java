package GAME.Menu;

import GAME.Game;

/**
 * Created by dot on 25-02-2016.
 */
public class NewGameMenu extends Menu {
    public NewGameMenu() {
        this.Add("Accept settings and start game", new MenuCallback() {
            public void Invoke() {
                Game game = new Game();
                game.startUp();
            }
        });
        this.Add("Player View Distance: 3-15", new MenuCallback() {
            public void Invoke() {
                //Change view distance via public setter
            }
        });
        this.Add("Monster density: X", new MenuCallback() {
            public void Invoke() {
                //Change monster density via public setter
            }
        });
        this.Add("More settings", new MenuCallback() {
            public void Invoke() {

            }
        });
    }
}
