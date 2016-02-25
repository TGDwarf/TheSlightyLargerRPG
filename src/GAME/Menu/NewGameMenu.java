package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class NewGameMenu extends Menu {
    public NewGameMenu() {
        this.Add("Accept settings and start game", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("Player View Distance", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("Monster density", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("More settings", new MenuCallback() {
            public void Invoke() {

            }
        });
    }
}
