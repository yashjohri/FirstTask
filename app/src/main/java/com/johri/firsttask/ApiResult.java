package com.johri.firsttask;

import java.util.ArrayList;

public class ApiResult {

    private ArrayList<FacilityItem> facilities;
    private ArrayList<ArrayList<FacilityOptionPair>> exclusions;

    public ApiResult() {
    }

    public ApiResult(ArrayList<FacilityItem> facilities, ArrayList<ArrayList<FacilityOptionPair>> exclusions) {
        this.facilities = facilities;
        this.exclusions = exclusions;
    }

    public ArrayList<FacilityItem> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<FacilityItem> facilities) {
        this.facilities = facilities;
    }

    public ArrayList<ArrayList<FacilityOptionPair>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(ArrayList<ArrayList<FacilityOptionPair>> exclusions) {
        this.exclusions = exclusions;
    }
}
