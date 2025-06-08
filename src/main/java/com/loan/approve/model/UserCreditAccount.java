package com.loan.approve.model;

import com.loan.approve.model.enums.CertificateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreditAccount {

    private String userName;
    private CertificateTypeEnum certificateTypeEnum;
    private String certificateId;
    private boolean hasCreditQuota;
    private Date validDateBegin;
    private Date validDateEnd;
    private double quotaAmount;
    private double usedAmount;
    private String currency;

}
