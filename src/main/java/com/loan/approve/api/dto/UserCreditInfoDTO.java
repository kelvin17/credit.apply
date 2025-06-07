package com.loan.approve.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreditInfoDTO {

    private String userId;
    private String userName;
    @Schema(description = "approvalStatus: None, Processing, DONE")
    private String approvalStatus;
    private boolean hasCreditQuota;
    private Date validDateBegin;
    private Date validDateEnd;
    private double quotaAmount;
    private double usedAmount;
    private String currency;
}
