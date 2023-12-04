package model;


import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;


public class YogaClass implements Writable {
    //represent single users budget, type of the yoga class and day, time they will attend
    private String day;
    private String time;
    private String yogaName;
    private int cost;
    private boolean removed;

    /*
     * REQUIRES:
     * day should be written as: Mon,Tue,Wed,Thu,Fri,Sat,Sun
     * time should be written as 24 hours clock cycle
     * yoga type should be clear
     * budget should be double
     *
     * EFFECTS: yoga types will be recorded cost should be deducted from the budget and updated
     * should give an error if the cost is more than the budget
     * day and time should be recorded
     */

    public YogaClass(String day, String time, String yogaName, int cost) {

        this.day = day;
        this.time = time;
        this.yogaName = yogaName;
        this.cost = cost;
        this.removed = false;

    }

    public boolean isRemoved() {
        return removed;
    }

    public void markAsRemoved() {
        removed = true;
    }

    //getters

    public String getDay() {

        return day;
    }

    public String getTime() {

        return time;

    }

    public String getYogaName() {

        return yogaName;

    }

    public int getCost() {

        return cost;
    }

    // EFFECTS: returns string representation of this yogaClass
    public String toString() {
        return "on " + day + " at " + time + " you have " + yogaName + " that costs " + cost;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("time", time);
        json.put("yogaName", yogaName);
        json.put("cost", cost);

        return json;
    }
}




