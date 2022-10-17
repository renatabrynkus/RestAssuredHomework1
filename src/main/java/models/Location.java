package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    Map<String, LocationModel> locations = new HashMap<>();

    @JsonAnyGetter
    public Map <String, LocationModel> getLocations() {
        return locations;
    }

    @JsonAnySetter
    void setLocations(String key, LocationModel value) {
        locations.put(key, value);
    }

    public List<LocationModel> getListOfLocations() {
        List<LocationModel> listOfLocations = new ArrayList<LocationModel>(locations.values());
        return listOfLocations;
    }
}