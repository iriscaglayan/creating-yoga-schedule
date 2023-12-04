package persistence;

import model.Schedule;
import model.YogaClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Source: Provided example JSonSerializationDemo

// Represents a reader that reads the yoga schedule from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS:reads yoga schedule and returns it;
    // throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses list of yoga classes from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Schedule s = new Schedule(name);
        addClasses(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses schedule from JSON object and adds them to classes
    private void addClasses(Schedule s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("yogaClasses");
        for (Object json : jsonArray) {
            JSONObject nextYogaClass = (JSONObject) json;
            addClass(s, nextYogaClass);
        }
    }

    // MODIFIES:
    // EFFECTS: parses schedule from JSON object and adds it to classes
    private void addClass(Schedule s, JSONObject jsonObject) {
        String day = jsonObject.getString("day");
        String time = jsonObject.getString("time");
        String yogaName = jsonObject.getString("yogaName");
        int cost = jsonObject.getInt("cost");

        YogaClass yogaClass = new YogaClass(day, time,yogaName, cost);
        s.addClass(yogaClass);
    }
}
