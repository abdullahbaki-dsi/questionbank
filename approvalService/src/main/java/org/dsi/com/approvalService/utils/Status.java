package org.dsi.com.approvalService.utils;

public enum Status {
    ACTIVE("A"),
    INACTIVE("D"),
    DRAFT("P");

    private String name;
    Status(String name) {
        this.name =name;
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        if(this.name.equalsIgnoreCase("A")) return "ACTIVE";
        if(this.name.equalsIgnoreCase("P")) return "DRAFT";
        if(this.name.equalsIgnoreCase("D")) return "INACTIVE";
        return name;
    }
    public static String getDesc(String statusCode) {
        String desc = Status.DRAFT.getDesc();

        if (statusCode.equals(Status.ACTIVE.name)) desc = Status.ACTIVE.getDesc();
        if (statusCode.equals(Status.INACTIVE.name)) desc = Status.INACTIVE.getDesc();
        return desc;
    }
}
