package org.dsi.com.approvalService.utils;

public enum Status {
    ACTIVE("A", "ACTIVE"),
    INACTIVE("D", "INACTIVE"),
    DRAFT("P", "DRAFT");

    private String code;
    private String description;

    Status(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getCode(String description) {

        for (Status status : Status.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid description: " + description);
    }
    public static String getDescription(String code) {
        for (Status status : Status.values()) {
            if (status.getCode().equals(code)) {
                return status.getDescription();
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}