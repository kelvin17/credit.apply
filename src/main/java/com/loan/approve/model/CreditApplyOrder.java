package com.loan.approve.model;

import com.loan.approve.model.enums.ApplyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplyOrder {
    String applyOrderId;
    String userId;
    ApplyStatus status;
}
