package GAME.Menu;

/**
 * Created by dot on 25-02-2016.
 */
public class BattleMenu extends Menu{
    public BattleMenu() {

        this.Add("Flee", new MenuCallback() {
            public void Invoke() {
                //Try to flee, chance to succeed, if so, take
            }
        });
        /*for (skill : skillList)
              {
                  this.Add(skill.name + "", new MenuCallback() {
                      public void Invoke() {
                         DO STUFF:  use skill
                      }
                  });
        }*/
    }
}
