package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Base {
    Map<String, Object> properties = new LinkedHashMap<>();

    @JsonAnySetter
    void setBaseProperties(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getBaseProperties() {
        return properties;
    }
}