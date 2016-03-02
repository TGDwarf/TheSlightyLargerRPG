package GAME;

/**
 * Created by dot on 25-02-2016.
 */
public class Player extends Entity {

    ConsolePrinter consolePrinter = new ConsolePrinter();

    private double Experience;
    private int Gold;

    public void setPotionUsed(boolean potionUsed) {
        this.potionUsed = potionUsed;
    }

    private boolean potionUsed = false;

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

    public Player(String name, String description, int level, Damage weapon){
        super(name, description, level, weapon);
    }

    public String potionsLeft(){
        if (potionUsed == true) {
            return "You have no potions left";
        }
        else {
            return "You have a potion to use";
        }
    }

    public void heal(){
        if (potionUsed == false){
            setHealth((int)Math.min(getHealth_Max(),getHealth() + (getHealth_Max()*0.6)));
            consolePrinter.print(getName() + "Drinks a potion and is now at " + getHealth());
            potionUsed = true;
        }
        else{
            consolePrinter.print("You have run out of potions!");
        }
    }

    public void endturnHeal(){
        if (getHealth() < getHealth_Max()){
            setHealth(getHealth()+0.5* this.Level);
            if (getHealth() > getHealth_Max()){
                setHealth(getHealth_Max());
            }
        }
    }

    public int attack(Entity monster, int attacktype){
        int attackDamage = (int)this.attackDamage(attacktype);
        monster.setHealth((monster.getHealth() > attackDamage) ? monster.getHealth() - attackDamage : 0);
        return attackDamage;
    }
}
