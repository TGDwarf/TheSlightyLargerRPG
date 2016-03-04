package GAME;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morten on 05-02-2016.
 *
 * Modified by dot on 29-02-2016.
 */

//TODO: Modify values to reflect name changes
public class WeaponDamage {
    private List<Double> damage = new ArrayList<>();

    private void axe(){
        damage.add(1.3);
        damage.add(7.2);
        damage.add(9.1);
    }
    private void dagger(){
        damage.add(1.5);
        damage.add(3.5);
        damage.add(12.4);
    }
    private void hands(){
        damage.add(1.5);
        damage.add(1.0);
        damage.add(3.1);

    }
    private void mace(){
        damage.add(1.2);
        damage.add(6.0);
        damage.add(12.9);

    }
    private void spear(){
        damage.add(1.2);
        damage.add(9.0);
        damage.add(14.6);

    }
    private void sword(){
        damage.add(1.4);
        damage.add(6.7);
        damage.add(13.3);
    }

    public List<Double> getSpeedAndDamage(WeaponTypes weapon){
        switch (weapon){
            case Axe:
                axe();
                break;
            case Dagger:
                dagger();
                break;
            case Hands:
                hands();
                break;
            case Mace:
                mace();
                break;
            case Spear:
                spear();
                break;
            case Sword:
                sword();
                break;
        }
        return damage;

    }

}