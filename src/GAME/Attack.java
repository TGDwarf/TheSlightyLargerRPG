package GAME;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Morten on 11-02-2016.
 *
 * Class that defines and calculate damage
 *
 * Modified by dot on 29-02-2016.
 */
public class Attack {

    private Damage damage;
    private double levelMultiplier = 1;
    private double calculatedDMG;
    private double multiplier;
    private List<String> skillList;

    /**
     * @return Calculated spin dmg
     */
    public double hack() {
        multiplier = 0.05 + levelMultiplier;
        calculatedDMG = calculateDMG() * multiplier;

        return calculatedDMG;
    }

    /**
     * @return Calculated spin dmg
     */
    public double spin()
    {
        multiplier = 0.02 + levelMultiplier;
        calculatedDMG = calculateDMG() * multiplier;
        return calculatedDMG;
    }

    /**
     * @return Calculated cleave dmg
     */
    public double cleave()
    {
        multiplier = 0.07 + levelMultiplier;
        calculatedDMG = calculateDMG() * multiplier;
        return calculatedDMG;
    }

    /**
     * @return returns random dmg between min and max
     */
    private double calculateDMG()
    {
        double randomDamage = ThreadLocalRandom.current().nextDouble(damage.getMinDamage(), damage.getMaxDamage());
        return randomDamage;
    }

    /**
     * @param damage initialize and get min / max dmg
     * @param level initialize leve for multiplier
     */
    public Attack(Damage damage, int level){
        this.damage = damage;
        this.levelMultiplier += (level * 0.04)  ;
    }
}
