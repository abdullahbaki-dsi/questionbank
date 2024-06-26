package org.dsi.com.questionService.utils;
public enum ApprovalStatus {
    PENDING(0, "PENDING"),
    REJECTED(1, "REJECTED"),
    APPROVED(2, "APPROVED"),
    REQUEST_CLARITY(3, "REQUEST_CLARITY");

    private int code;
    private String description;

    ApprovalStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(int code) {
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if (status.getCode() == code) {
                return status.getDescription();
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static int getCode(String description) {
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status.getCode();
            }
        }


        throw new IllegalArgumentException("Invalid description: " + description);
    }

}