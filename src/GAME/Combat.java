package GAME;

import GAME.Menu.BattleMenu;

import java.util.Scanner;

/**
 * Created by dot on 25-02-2016.
 */
public class Combat {
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    Scanner in = new Scanner(System.in);

    public Entity getWinner() {
        return winner;
    }

    private Entity winner;

    public void playerAttack(Creature creature, Player player, int attackOption){
        int AttackDamage = player.attack(creature,attackOption);

        consolePrinter.print(String.format("  " + creature.getName() + " is hit for %d HP of damage (%s)\n", AttackDamage, creature.status()));

        if (creature.getHealth() <= 0){
            creature.setAlive(false);
            consolePrinter.print("  " + creature.getName() + " has been defeated");
            winner = player;
            player.setPotionUsed(false);
            System.out.println("You have won the battle.\nPress enter to continue...");
            in.nextLine();

        }
    }

    public void creatureAttack(Creature creature, Player player){
        int AttackDmg = creature.attack(player);

        consolePrinter.print(String.format("  " + player.getName() + " is hit for %d HP of damage (%s)\n", AttackDmg, player.status()));

        if (player.getHealth() <= 0){
            player.setAlive(false);
            consolePrinter.print("  " + player.getName() + " has been defeated");
            winner = creature;
            System.out.println("You have lost the battle.\nPress enter to continue...");
            in.nextLine();
        }
    }

    public Combat(Player player, Creature creature) {

        consolePrinter.print("You encounter " + creature.getName() + ": " + creature.getDescription());
        consolePrinter.print("Battle with " + creature.getName() + " starts (" + player.status() + " / " + creature.status() + ")");
        while (player.isAlive() && creature.isAlive()) {

            // The one with the faster attack speed goes first
            if (creature.getWeapon().getAttackSpeed() > player.getWeapon().getAttackSpeed()) {
                creatureAttack(creature, player);
                if (player.isAlive()) {
                    BattleMenu menu = new BattleMenu(this, player, creature);
                    menu.Show();
                }
            }
            else {
                BattleMenu menu = new BattleMenu(this, player, creature);
                menu.Show();
                if (creature.isAlive()) {
                    creatureAttack(creature, player);
                }
            }

        }
    }
}
