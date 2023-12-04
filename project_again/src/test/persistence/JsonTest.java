package persistence;

import model.Schedule;
import model.YogaClass;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkYogaClass(String day, String time, String yogaName, int cost, YogaClass yogaclass) {
        assertEquals(day, yogaclass.getDay());
        assertEquals(time, yogaclass.getTime());
        assertEquals(yogaName, yogaclass.getYogaName());
        assertEquals(cost, yogaclass.getCost());

    }
}