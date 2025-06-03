package com.loan.approve.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreditAccount {

    private String userId;
    private String userName;
    private boolean hasCreditQuota;
    private Date validDateBegin;
    private Date validDateEnd;
    private double quotaAmount;
    private double usedAmount;
    private String currency;

}
