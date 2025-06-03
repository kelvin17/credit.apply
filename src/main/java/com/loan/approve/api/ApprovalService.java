package com.loan.approve.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans/approval")
public class ApprovalService {

    @PostMapping("/submit")
    public String submitApproval() {
        return "Approved";
    }

    @PostMapping("/query")
    public String queryProgress() {
        return "Processing";
    }
}
