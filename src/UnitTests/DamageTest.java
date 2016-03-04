package UnitTests;

import GAME.Damage;
import GAME.WeaponTypes;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by dot on 03-03-2016.
 */
public class DamageTest {

    @Test
    public void testGetAttackSpeed() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        assertEquals(1.5, dmg.getAttackSpeed(),0);
    }

    @Test
    public void testGetMaxDamage() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        assertEquals(3.1, dmg.getMaxDamage(),0);
    }

    @Test
    public void testGetMinDamage() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        assertEquals(1.0, dmg.getMinDamage(),0);
    }
}
