package com.loan.approve.service;

import com.loan.approve.api.dto.ApprovalRequest;
import org.springframework.stereotype.Service;

@Service
public class CreditApplyService {

    public String addNewApply(ApprovalRequest approvalRequest) {
        return "approvalID";
    }

    public String queryApprovalInfo(String username, String certificateNo, String certificateType) {
        return "approvalID";
    }
}
