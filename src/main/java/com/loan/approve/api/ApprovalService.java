package com.loan.approve.api;

import com.loan.approve.api.dto.ApprovalRequest;
import com.loan.approve.api.dto.BaseResult;
import com.loan.approve.api.dto.UserCreditInfoDTO;
import com.loan.approve.model.UserCreditAccount;
import com.loan.approve.service.CreditApplyService;
import com.loan.approve.service.CreditManageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/loans/credit/application")
@Tag(name = "User Interface", description = "API relative to user")
public class ApprovalService {

    @Autowired
    private CreditApplyService creditApplyService;

    @Autowired
    private CreditManageService creditManageService;

    @PostMapping("/submit")
    public BaseResult<String> submitApproval(ApprovalRequest approvalRequest) {
        try {
            String approvalID = creditApplyService.addNewApply(approvalRequest);
            return BaseResult.success(approvalID);
        } catch (Exception e) {
            return BaseResult.fail("001", e.getMessage());
        }
    }

    @PostMapping("/query")
    public BaseResult<UserCreditInfoDTO> queryProgress(ApprovalRequest request) {
        try {
            UserCreditAccount userCreditAccount = creditManageService.queryUserCreditAccount(request.getUserName(), request.getCertificateType(), request.getCertificateID());
            UserCreditInfoDTO dto = new UserCreditInfoDTO();
            if (userCreditAccount != null && dto.getValidDateEnd().after(new Date())) {
                dto.setUserName(userCreditAccount.getUserName());
                dto.setUserId(userCreditAccount.getUserId());
                dto.setValidDateBegin(userCreditAccount.getValidDateBegin());
                dto.setValidDateEnd(userCreditAccount.getValidDateEnd());
                dto.setApprovalStatus("DONE");
                if (userCreditAccount.isHasCreditQuota()) {
                    dto.setHasCreditQuota(true);
                    dto.setQuotaAmount(userCreditAccount.getQuotaAmount());
                    dto.setCurrency(userCreditAccount.getCurrency());
                } else {
                    dto.setHasCreditQuota(false);
                }

            } else {
                String approvalId = creditApplyService.queryApprovalInfo(request.getUserName(), request.getCertificateType(), request.getCertificateID());
                if (StringUtils.isBlank(approvalId)) {
                    dto.setApprovalStatus("NONE");
                } else {
                    dto.setApprovalStatus("Processing");
                }
            }

            return BaseResult.success(dto);
        } catch (Exception e) {
            return BaseResult.fail("001", e.getMessage());
        }
    }
}
