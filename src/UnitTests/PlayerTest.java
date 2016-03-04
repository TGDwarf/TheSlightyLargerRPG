package UnitTests;

import GAME.Creature;
import GAME.Damage;
import GAME.Player;
import GAME.WeaponTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dot on 03-03-2016.
 */
public class PlayerTest {

    int playerLevel = 10;

    @Test
    public void testHeal() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player player = new Player("testPlayer","The Player",playerLevel,dmg);
        player.setHealth(5);
        player.heal();
        assertTrue(player.getHealth() > 5 );
    }

    @Test
    public void testAttack() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player player = new Player("testPlayer","The Player",playerLevel,dmg);
        Creature creature = new Creature("Troll", "A hideous being with bad breath", 1, new Damage(WeaponTypes.Hands));
        double initMonsterHP = creature.getHealth();
        player.attack(creature,1);
        assertTrue(initMonsterHP > creature.getHealth());

    }
    @Test
    public void testSetWeapon() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player player = new Player("testPlayer","The Player",playerLevel,dmg);
        Damage dmgAltered = new Damage(WeaponTypes.Sword);
        player.setWeapon(dmgAltered);
        assertEquals(dmgAltered, player.getWeapon());
    }

    @Test
    public void testGetWeapon() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertEquals(dmg, entity.getWeapon());
    }

    @Test
    public void testSetHitpoint() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        entity.setHealth(200);
        assertEquals(200,entity.getHealth(),0);
    }

    @Test
    public void testGetHitpoint() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertEquals(playerLevel*15,entity.getHealth(),playerLevel*2);
    }

    @Test
    public void testGetLevel() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertEquals(playerLevel,entity.getLevel());
    }

    @Test
    public void testGetName() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertEquals("testPlayer",entity.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertEquals("The Player",entity.getDescription());
    }

    @Test
    public void testAttackDamage() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertTrue(entity.attackDamage(1) > 1 && entity.attackDamage(1) < 4.3);
    }

    @Test
    public void testAlive() throws Exception {
        Damage dmg = new Damage(WeaponTypes.Hands);
        Player entity = new Player("testPlayer","The Player",playerLevel,dmg);
        assertTrue(entity.isAlive());
    }
}
