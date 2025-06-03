package com.loan.approve.model.enums;

import lombok.Getter;

@Getter
public enum DataItemName {

    INCOME_MONTHLY("INCOME_MONTHLY", 7, 365),
    INCOME_ANNUAL("INCOME_ANNUAL", 7, 365),
    HOUSE("HOUSE", 7, 365 * 5),
    TAX("TAX", 7, 365);

    private final String itemName;
    private final Integer taskExpireDays;
    private final Integer dataExpireDays;

    DataItemName(String itemName, Integer taskExpireDays, Integer dataExpireDays) {
        this.itemName = itemName;
        this.taskExpireDays = taskExpireDays;
        this.dataExpireDays = dataExpireDays;
    }


}
