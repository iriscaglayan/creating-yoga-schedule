package persistence;

import model.Schedule;
import model.YogaClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Schedule s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchedule");
        try {
            Schedule s = reader.read();
            assertEquals("My schedule", s.getName());
            assertEquals(0, s.numThingies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchedule.json");
        try {
            Schedule s = reader.read();
            assertEquals("My schedule", s.getName());
            List<YogaClass> yogaClasses = s.getYogaClasses();
            assertEquals(2, yogaClasses.size());
            checkYogaClass ("mon", "12:00", "hatha", 40, yogaClasses.get(0));
            checkYogaClass ("tue", "12:30", "relax", 60, yogaClasses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}