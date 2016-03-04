package UnitTests;

import GAME.Creature;
import GAME.Damage;
import GAME.Player;
import GAME.WeaponTypes;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dot on 03-03-2016.
 */
public class CreatureTest {
    @Test
    public void testAttack() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Creature creature = new Creature("monster_name","creature description",100,dmg);
        Player player = new Player("player_name","player description",100,dmg);
        creature.attack(player);
        assertTrue(player.getHealth() != 100);
    }
}
