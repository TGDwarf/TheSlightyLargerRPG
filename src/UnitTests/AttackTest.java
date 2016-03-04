package UnitTests;


import GAME.Attack;
import GAME.Damage;
import GAME.WeaponTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dot on 03-03-2016.
 */
public class AttackTest {
    Damage dmg;
    Attack at;
    @Before
    public void setUp() throws Exception {
        dmg = new Damage(WeaponTypes.Hands);
        at = new Attack (dmg, 1);
    }

    @Test
    public void testHack() throws Exception {
        assertEquals(1, at.hack(),2.379);
    }

    @Test
    public void testSpin() throws Exception {
        assertEquals(1, at.spin(),2.286);
    }

    @Test
    public void testCleave() throws Exception {
        assertEquals(1, at.cleave(),2.441);
    }
}