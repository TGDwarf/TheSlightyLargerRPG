package GAME;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dot on 25-02-2016.
 */
public class Entity {

    private String Name;
    private String Description;
    private String NamePlural;

    private Point Location;

    public int Level = 1;
    private double Health_Max;
    private double Health;
    private boolean Alive;

    private Damage weapon;

    /**
     * Constructor
     * @param name the name of the entity
     * @param description the description for the entity
     * @param level the initial level
     * @param weapon the start weapon
     */
    public Entity(String name, String description, int level, Damage weapon){
        this.Name = name;
        this.Description = description;
        this.Level = level;
        this.Health_Max = calculateMaxHealth();
        this.Health = Health_Max;
        this.Alive = true;
        this.weapon = weapon;
    }

    /**
     * @return makes the game a bit more random by not using a flag hp value pr level
     */
    public double calculateMaxHealth(){
        return Level * ThreadLocalRandom.current().nextInt(13, 17);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Point getLocation() {
        return Location;
    }

    public void setLocation(Point location) {
        Location = location;
    }

    public double getHealth_Max() {
        return Health_Max;
    }

    public void setHealth_Max(double health_Max) {
        Health_Max = health_Max;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public boolean isAlive() {
        return Alive;
    }

    public void setAlive(boolean alive) {
        this.Alive = alive;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNamePlural() {
        return NamePlural;
    }

    public void setNamePlural(String namePlural) {
        NamePlural = namePlural;
    }

    public String status(){
        return String.format(Name + " HP: " + Health);
    }

    public Damage getWeapon() {
        return weapon;
    }

    public void setWeapon(Damage weapon) {
        this.weapon = weapon;
    }

    public double attackDamage(int attackType){
        Attack attack = new Attack(weapon, Level);
        double damage = 0;
        switch (attackType){
            case 1:
                damage = attack.hack();
                break;
            case 2:
                damage = attack.cleave();
                break;
            case 3:
                damage = attack.spin();
                break;
        }
        return damage;
    }

}
