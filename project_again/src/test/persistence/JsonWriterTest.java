package persistence;

import model.YogaClass;
import model.Schedule;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest{
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    String day;
    String time;
    String yogaName;
    int cost;

    @Test
    void testWriterInvalidFile() {
        try {
            Schedule s = new Schedule("My schedule");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySchedule() {
        try {
            Schedule s = new Schedule("My schedule");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchedule");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySchedule");
            s = reader.read();
            assertEquals("My schedule", s.getName());
            assertEquals(0, s.numThingies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSchedule() {
        try {
            Schedule s = new Schedule("My schedule");
            s.addClass(new YogaClass("wed", "12:30", "hatha", 40));
            s.addClass(new YogaClass("thu", "13:30", "chill", 160));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchedule.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSchedule.json");
            s = reader.read();
            assertEquals("My schedule", s.getName());
            List<YogaClass> yogaClasses = s.getYogaClasses();
            assertEquals(2, yogaClasses.size());
            checkYogaClass ("wed", "12:30", "hatha", 40, yogaClasses.get(0));
            checkYogaClass ("thu", "13:30", "chill", 160, yogaClasses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}