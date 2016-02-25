package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class MainMenu extends Menu {
    public MainMenu() {
        this.Add("New Game", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("Save Game", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("Load Game", new MenuCallback() {
            public void Invoke() {

            }
        });
        this.Add("Quit Game", new MenuCallback() {
            public void Invoke() {
                System.exit(0);
            }
        });
    }
}
