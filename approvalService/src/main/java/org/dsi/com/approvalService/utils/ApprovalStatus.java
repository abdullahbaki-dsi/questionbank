package org.dsi.com.approvalService.utils;

public enum ApprovalStatus {
    REJECTED(0),
    APPROVED(1),
    PENDING(2),
    REQUEST_CLARITY(3);
    private int status;
    ApprovalStatus(int status) {
        this.status =status;
    }

    public int getstatus(){
        return status;
    }

    public String getDesc(){
        if(this.status==0) return "REJECTED";
        if(this.status==1) return "APPROVED";
        if(this.status==2) return "PENDING";
        if(this.status==3) return "REQUEST_CLARITY";
        return "";
    }
}
