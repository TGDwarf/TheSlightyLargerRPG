package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class SaveGameMenu extends Menu{
    public SaveGameMenu() {
        //Method to search dir and find savefiles, store in saveFiles list

        this.Add("New Save File", new MenuCallback() {
            public void Invoke() {

            }
        });
        /*for (saveFile : saveFiles)
              {
                  this.Add(saveFile.name + "", new MenuCallback() {
                      public void Invoke() {
                          overwrite savefile
                      }
                  });
        }*/
    }
}
