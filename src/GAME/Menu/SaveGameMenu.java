package GAME.Menu;

import GAME.Game;
import GAME.Input;
import GAME.MapGenerator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by dot on 25-02-2016.
 */
//TODO: when entering the save menu, should have the mapgerator.borderedmap object along
public class SaveGameMenu extends Menu{
    public SaveGameMenu() {
        //Method to search dir and find savefiles, store in saveFiles list

        this.Add("New Save File", new MenuCallback() {
            public void Invoke() {
                String saveFileName = Input.getKeyboardInput();
                try{

                    FileOutputStream fileOutStream = new FileOutputStream("c:\\address.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fileOutStream);
                 //   oos.writeObject(mapGenerator.borderedMap);
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
        this.Add("Back to main menu", new MenuCallback() {
            public void Invoke() {

            }
        });
    }
}
