package com.loan.approve.api;

import com.loan.approve.api.dto.ApprovalRequest;
import com.loan.approve.api.dto.BaseResult;
import com.loan.approve.api.dto.UserCreditInfoDTO;
import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.model.UserCreditAccount;
import com.loan.approve.service.CreditApplyService;
import com.loan.approve.service.CreditManageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BaseResult<String> submitApproval(@RequestBody ApprovalRequest approvalRequest) {
        try {
            System.out.printf("new submitApproval, userName=%s, userProfileID:%s\n", approvalRequest.getUserName(),
                    approvalRequest.getCertificateID());
            CreditApplyOrder creditApplyOrder = CreditApplyOrder.fromDTO(approvalRequest);
            String approvalID = creditApplyService.addNewApply(creditApplyOrder);
            System.out.printf("new submitApproval end, approvalID=%s\n", approvalID);
            return BaseResult.success(approvalID);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.printf("new submitApproval exception, error: %s\n", e.getMessage());
            return BaseResult.fail("001", e.getMessage());
        }
    }

    @PostMapping("/query")
    public BaseResult<UserCreditInfoDTO> queryProgress(@RequestBody ApprovalRequest request) {
        try {
            System.out.printf("new query, userName=%s\n", request.getUserName());
            UserCreditAccount userCreditAccount = creditManageService.queryUserCreditAccount(request.getUserName(), request.getCertificateType(), request.getCertificateID());
            UserCreditInfoDTO dto = new UserCreditInfoDTO();
            if (userCreditAccount != null && userCreditAccount.getValidDateEnd().after(new Date())) {
                dto.setUserName(userCreditAccount.getUserName());
                dto.setCertificateType(userCreditAccount.getCertificateTypeEnum().name());
                dto.setCertificateNo(userCreditAccount.getCertificateId());

                dto.setValidDateBegin(userCreditAccount.getValidDateBegin());
                dto.setValidDateEnd(userCreditAccount.getValidDateEnd());
                dto.setApprovalStatus("DONE");
                if (userCreditAccount.isHasCreditQuota()) {
                    dto.setHasCreditQuota(true);
                    dto.setQuotaAmount(userCreditAccount.getQuotaAmount());
                    dto.setUsedAmount(userCreditAccount.getUsedAmount());
                    dto.setCurrency(userCreditAccount.getCurrency());
                } else {
                    dto.setHasCreditQuota(false);
                }
            } else {
                CreditApplyOrder creditApplyOrder = creditApplyService.queryApprovalInfo(request.getUserName(), request.getCertificateType(), request.getCertificateID());
                if (creditApplyOrder == null) {
                    dto.setApprovalStatus("NONE");
                } else {
                    dto.setUserName(creditApplyOrder.getUserName());
                    dto.setCertificateType(creditApplyOrder.getCertificateTypeEnum().name());
                    dto.setCertificateNo(creditApplyOrder.getCertificateId());
                    dto.setApprovalStatus("Processing, Phase:" + creditApplyOrder.getStatus().name());
                }
            }

            System.out.printf("new query end, userName=%s\n", dto);
            return BaseResult.success(dto);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.printf("new query exception, error: %s\n", e.getMessage());
            return BaseResult.fail("001", e.getMessage());
        }
    }
}
