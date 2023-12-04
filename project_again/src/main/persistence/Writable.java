package persistence;

import org.json.JSONObject;

// Source: Provided example JSonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

