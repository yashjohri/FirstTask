package com.johri.firsttask;

public class ExclusionItem {

    private String facility_id_1, facility_id_2, option_id_1, option_id_2;

    public ExclusionItem(String facility_id_1, String facility_id_2, String option_id_1, String option_id_2) {
        this.facility_id_1 = facility_id_1;
        this.facility_id_2 = facility_id_2;
        this.option_id_1 = option_id_1;
        this.option_id_2 = option_id_2;
    }

    public String getFacility_id_1() {
        return facility_id_1;
    }

    public String getFacility_id_2() {
        return facility_id_2;
    }

    public String getOption_id_1() {
        return option_id_1;
    }

    public String getOption_id_2() {
        return option_id_2;
    }
}
