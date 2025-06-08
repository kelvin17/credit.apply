package com.loan.approve.model.enums;

public enum DataCollectTaskStatus {
    INIT("INIT", "begin to collect data"),
    TASK_EXPIRED("TASK_EXPIRED", "expired"),
    COMPLETED("COMPLETED", "collect enough information"),
    NOT_COMPLETED("NOT_COMPLETED", "not collect enough information"),
    ;

    final String code;
    final String desc;

    DataCollectTaskStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
