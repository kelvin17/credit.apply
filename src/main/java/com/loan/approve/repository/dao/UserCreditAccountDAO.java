package com.loan.approve.repository.dao;

import lombok.Data;

import java.util.Date;

@Data
public class UserCreditAccountDAO {
    private String userId;
    private String userName;
    private boolean hasCreditQuota;
    private Date validDateBegin;
    private Date validDateEnd;
    private double quotaAmount;
    private double usedAmount;
    private String currency;
}
