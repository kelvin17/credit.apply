package com.loan.approve.model;

import com.loan.approve.api.dto.ApprovalRequest;
import com.loan.approve.model.enums.ApplyStatus;
import com.loan.approve.model.enums.CertificateTypeEnum;
import com.loan.approve.utils.OrderIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplyOrder {
    private String applyOrderId;
    //    private String userId;
    private ApplyStatus status;
    private String userName;
    private CertificateTypeEnum certificateTypeEnum;
    private String certificateID;
    private String phoneNumber;
    private String email;

    public static CreditApplyOrder fromDTO(ApprovalRequest request) {
        CreditApplyOrder creditApplyOrder = new CreditApplyOrder();
        creditApplyOrder.setUserName(request.getUserName());
        creditApplyOrder.setCertificateTypeEnum(CertificateTypeEnum.valueOf(request.getCertificateType()));
        creditApplyOrder.setCertificateID(request.getCertificateID());
        creditApplyOrder.setPhoneNumber(request.getPhoneNumber());
        creditApplyOrder.setEmail(request.getEmail());
        creditApplyOrder.setStatus(ApplyStatus.INIT);
        creditApplyOrder.setApplyOrderId(OrderIdGenerator.generate(request.getUserName(), OrderIdGenerator.APPROVAL));
        return creditApplyOrder;
    }
}
