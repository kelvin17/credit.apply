package com.loan.approve.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalRequest {
    private String userName;
    private String certificateType;
    private String certificateID;
    private String phoneNumber;
    private String email;
}
