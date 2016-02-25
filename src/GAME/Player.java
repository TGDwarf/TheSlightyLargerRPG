package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class Player extends Entity {

    private double Experience;
    private int Gold;
    private int Level;

    public double getExperience() {
        return Experience;
    }

    public void setExperience(double experience) {
        Experience = experience;
    }

    public int getGold() {
        return Gold;
    }

    public void setGold(int gold) {
        Gold = gold;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
