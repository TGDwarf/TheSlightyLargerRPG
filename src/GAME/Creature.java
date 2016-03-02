package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class Creature extends Entity {

    public Creature(String name, String description, int level, Damage weapon) {
        super(name, description, level, weapon);
    }

    public int attack(Player player) {
        int attackDamage = (int) this.attackDamage(1);
        player.setHealth((player.getHealth() > attackDamage) ? player.getHealth() - attackDamage : 0);
        return attackDamage;
    }
}
