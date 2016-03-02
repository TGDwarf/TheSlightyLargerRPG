package GAME.Menu;

import GAME.Combat;
import GAME.Creature;
import GAME.Player;

/**
 * Created by dot on 25-02-2016.
 */
public class BattleMenu extends Menu{
    public BattleMenu(Combat combat, Player player, Creature creature) {
        this.Add("Hack", new MenuCallback() {
            public void Invoke() {
                combat.playerAttack(creature, player, 1);
            }
        });
        this.Add("Spin", new MenuCallback() {
            public void Invoke() {
                combat.playerAttack(creature, player, 2);
            }
        });
        this.Add("Cleave", new MenuCallback() {
            public void Invoke() {
                combat.playerAttack(creature, player, 3);
            }
        });
        this.Add("Heal - " + player.potionsLeft(), new MenuCallback() {
            public void Invoke() {
                player.heal();
            }
        });
        this.Add("Flee", new MenuCallback() {
            public void Invoke() {
                //Try to flee, chance to succeed, if so no dmg and combat stops with you returning to origin location, if not forfeit turn
            }
        });
    }
}
