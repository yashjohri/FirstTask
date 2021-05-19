package com.johri.firsttask.ListItems;

import java.util.ArrayList;

public class FacilityItem {

    private String name, facility_id;
    private ArrayList<FacilityOptionItem> options;

    public FacilityItem() {
    }

    public FacilityItem(String name, String facility_id, ArrayList<FacilityOptionItem> options) {
        this.name = name;
        this.facility_id = facility_id;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public ArrayList<FacilityOptionItem> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<FacilityOptionItem> options) {
        this.options = options;
    }
}
