package GAME.Menu;

import GAME.Game;
import GAME.Input;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by dot on 25-02-2016.
 */
public class SaveGameMenu extends Menu{

    Input input = new Input();

    public SaveGameMenu(Game game) {
        //Method to search dir and find save files, store in saveFiles list

        this.Add("Back to main menu", new MenuCallback() {
            public void Invoke() {
            }
        });
        this.Add("New Save File - DOES NOT WORK", new MenuCallback() {
            public void Invoke() {
                String saveFileName = input.inGameGetKeyboardInput();
                try{

                    FileOutputStream fileOutStream = new FileOutputStream("c:\\" + saveFileName + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fileOutStream);
                    oos.writeObject(game);
                    oos.close();
                    System.out.println("Done");
                 //   Return to save menu

                }catch(Exception ex){
                    ex.printStackTrace();
                }

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
