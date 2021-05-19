package com.johri.firsttask.Utils;

public class FacilityOptionPair {

    String facility_id, options_id;

    public FacilityOptionPair() {
    }

    public FacilityOptionPair(String facility_id, String options_id) {
        this.facility_id = facility_id;
        this.options_id = options_id;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getOptions_id() {
        return options_id;
    }

    public void setOptions_id(String options_id) {
        this.options_id = options_id;
    }
}
