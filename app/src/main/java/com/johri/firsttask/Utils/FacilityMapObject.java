package com.johri.firsttask.Utils;

import com.johri.firsttask.ListItems.FacilityOptionItem;

import java.util.HashMap;

public class FacilityMapObject {

    String facility_name, facility_id;
    HashMap<String, FacilityOptionItem> optionsMap;

    public FacilityMapObject(String facility_name, String facility_id, HashMap<String, FacilityOptionItem> optionsMap) {
        this.facility_name = facility_name;
        this.facility_id = facility_id;
        this.optionsMap = optionsMap;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public HashMap<String, FacilityOptionItem> getOptionsMap() {
        return optionsMap;
    }
}
