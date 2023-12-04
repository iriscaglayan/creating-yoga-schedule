package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Represents a schedule having a yoga class schedule
public class Schedule implements Writable {
    private String name;
    private List<YogaClass> yogaClasses;
    private int totalCostEverything;



    // EFFECTS: constructs schedule with a name and empty list of thingies
    public Schedule(String name) {
        this.name = name;

        yogaClasses = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds yogaClass to the schedule
    public void addClass(YogaClass yogaClass) {
        yogaClasses.add(yogaClass);
        EventLog.getInstance().logEvent(new Event("Yoga Class is created"));
    }

    // MODIFIES: this
    //Requires: there has to be a registered yogaClass with the same info
    // EFFECTS: remove yogaClass from the schedule
    // I choose to iterator because while I am iterating the list I am trying to remove an object
    public String removeClass(String day, String time, String yogaName, int cost) {
        String schedulePrint = "";
        Iterator<YogaClass> iterator = yogaClasses.iterator();
        while (iterator.hasNext()) {
            YogaClass yogaClass = iterator.next();
            if (yogaClass.getDay().equals(day)
                    && yogaClass.getTime().equals(time)
                    && yogaClass.getYogaName().equals(yogaName)
                    && yogaClass.getCost() == cost) {
                yogaClass.markAsRemoved();
                schedulePrint = "Yoga class has been removed";
                iterator.remove();
                EventLog.getInstance().logEvent(new Event("Yoga Class is removed"));
            }
        }

        if (yogaClasses.isEmpty()) {
            schedulePrint = "The list is empty";
        }
        return schedulePrint;
    }


    // MODIFIES: this
    //REQUIRES: is.removed = false
    //EFFECT: print the yoga classes in the schedule
    public String scheduleToString() {
        StringBuilder scheduleBuilder = new StringBuilder();
        for (YogaClass c : yogaClasses) {
            if (!c.isRemoved()) {
                return ("\nday:" + c.getDay() + "\ntime: " + c.getTime()
                        + "\n yoga Name: " + c.getYogaName() + "\n" + "cost: " + c.getCost() + "\n");
            }
            scheduleBuilder.append("\n");
            EventLog.getInstance().logEvent(new Event("Yoga Schedule is printed"));
        }
        return scheduleBuilder.toString();
    }


    // MODIFIES: this
    //REQUIRES: is.removed = false
    //EFFECT: add the cost of yoga classes and updates to totalCost

    public int totalCostYogaClass() {
        int totalCost = 0;
        for (YogaClass c : yogaClasses) {
            if (!c.isRemoved()) {
                totalCost += c.getCost();
                EventLog.getInstance().logEvent(new Event("Total yoga cost is printed"));
            }

        }
        totalCostEverything = totalCost;
        return totalCost;
    }




    // EFFECTS: returns an unmodifiable list of yogaclasses in this schedule
    public List<YogaClass> getYogaClasses() {

        return Collections.unmodifiableList(yogaClasses);
    }



    // EFFECTS: returns number of classes in this schedule
    public int numThingies() {
        return yogaClasses.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("yogaClasses", yogaClassesToJson());
        return json;
    }

    // EFFECTS: returns things in this schedule as a JSON array
    private JSONArray yogaClassesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (YogaClass yc: yogaClasses) {
            jsonArray.put(yc.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: prints the events from the event log
    public void logPrinter(EventLog eventLog) {
        for (Event e : eventLog) {
            System.out.println(e.getDescription());
        }
    }
}

