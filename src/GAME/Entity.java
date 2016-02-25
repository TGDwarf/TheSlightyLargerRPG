package GAME;

import java.awt.*;

/**
 * Created by dot on 25-02-2016.
 */
public class Entity {
    private String Name;
    private String Description;
    private String NamePlural;

    private Point Location;

    private double Health_Max;
    private double Health;
    private double Base_Dmg;

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
}
