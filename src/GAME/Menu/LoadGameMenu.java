package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class LoadGameMenu extends Menu {
    public LoadGameMenu() {
        //Method to search dir and find savefiles, store in loadFiles list

        this.Add("Placeholder for load files", new MenuCallback() {
            public void Invoke() {

            }
        });

        /*for (loadFile : loadFiles)
              {
                  this.Add(saveFile.name + "", new MenuCallback() {
                      public void Invoke() {
                          overwrite savefile
                      }
                  });
        }*/
    }
}
