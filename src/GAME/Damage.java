package GAME;

import java.util.List;

/**
 * Created by Morten on 04-02-2016.
 *
 * Modified by dot on 29-02-2016.
 */
public class Damage {
    private double minDamage;
    private double maxDamage;
    private double attackSpeed;

    public Damage(WeaponTypes weapon){
        WeaponDamage wd = new WeaponDamage();
        List<Double> damage = wd.getSpeedAndDamage(weapon);
        this.attackSpeed = damage.get(0);
        this.minDamage = damage.get(1);
        this.maxDamage = damage.get(2);

    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getMaxDamage() {
        return maxDamage;
    }

    public double getMinDamage() {
        return minDamage;
    }
}