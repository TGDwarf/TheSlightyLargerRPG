package UnitTests;


import GAME.WeaponDamage;
import GAME.WeaponTypes;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by dot on 03-03-2016.
 */
public class WeaponTest {
    @Test
    public void testGetSpeedAndDamageForAxe() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Axe);
        //verify speed
        assertEquals(1.3, dmg.get(0),0);
        //verify min damage
        assertEquals(7.2, dmg.get(1),0);
        //verify max damage
        assertEquals(9.1, dmg.get(2),0);
    }

    @Test
    public void testGetSpeedAndDamageForDagger() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Dagger);
        //verify speed
        assertEquals(1.5, dmg.get(0),0);
        //verify min damage
        assertEquals(3.5, dmg.get(1),0);
        //verify max damage
        assertEquals(12.4, dmg.get(2),0);
    }

    @Test
    public void testGetSpeedAndDamageForHands() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Hands);
        //verify speed
        assertEquals(1.5, dmg.get(0),0);
        //verify min damage
        assertEquals(1.0, dmg.get(1),0);
        //verify max damage
        assertEquals(3.1, dmg.get(2),0);
    }

    @Test
    public void testGetSpeedAndDamageForMace() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Mace);
        //verify speed
        assertEquals(1.2, dmg.get(0),0);
        //verify min damage
        assertEquals(6.0, dmg.get(1),0);
        //verify max damage
        assertEquals(12.9, dmg.get(2),0);
    }

    @Test
    public void testGetSpeedAndDamageForSpear() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Spear);
        //verify speed
        assertEquals(1.2, dmg.get(0),0);
        //verify min damage
        assertEquals(9.0, dmg.get(1),0);
        //verify max damage
        assertEquals(14.6, dmg.get(2),0);
    }

    @Test
    public void testGetSpeedAndDamageForSword() throws Exception {
        WeaponDamage wd = new WeaponDamage();
        List<Double> dmg = wd.getSpeedAndDamage(WeaponTypes.Sword);
        //verify speed
        assertEquals(1.4, dmg.get(0),0);
        //verify min damage
        assertEquals(6.7, dmg.get(1),0);
        //verify max damage
        assertEquals(13.3, dmg.get(2),0);
    }
}
