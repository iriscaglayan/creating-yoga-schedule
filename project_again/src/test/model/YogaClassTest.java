package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class YogaClassTest {
    private YogaClass yogaClass;

    @BeforeEach
    public void setUp() {
        yogaClass = new YogaClass("Mon", "12:00", "Hatha Yoga", 20);
    }

    @Test
    public void testGetDay() {
        assertEquals("Mon", yogaClass.getDay());
    }

    @Test
    public void testGetTime() {
        assertEquals("12:00", yogaClass.getTime());
    }

    @Test
    public void testGetYogaName() {
        assertEquals("Hatha Yoga", yogaClass.getYogaName());
    }

    @Test
    public void testGetCost() {
        assertEquals(20, yogaClass.getCost());
    }

    @Test
    public void testToString() {
        assertEquals("on Mon at 12:00 you have Hatha Yoga that costs 20", yogaClass.toString());
    }

    @Test
    public void testToJson() {
        JSONObject json = yogaClass.toJson();
        assertEquals("Mon", json.getString("day"));
        assertEquals("12:00", json.getString("time"));
        assertEquals("Hatha Yoga", json.getString("yogaName"));
        assertEquals(20, json.getInt("cost"));
    }
}
