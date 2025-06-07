package com.loan.approve.model;

import com.loan.approve.api.dto.ApprovalRequest;
import com.loan.approve.model.enums.ApplyStatus;
import com.loan.approve.model.enums.CertificateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplyOrder {
    private String applyOrderId;
    private String userId;
    private ApplyStatus status;
    private String userName;
    private CertificateTypeEnum certificateTypeEnum;
    private String certificateID;
    private String phoneNumber;
    private String email;

    public static CreditApplyOrder fromDTO(ApprovalRequest request) {
        CreditApplyOrder creditApplyOrder = new CreditApplyOrder();
        BeanUtils.copyProperties(request, creditApplyOrder);
        creditApplyOrder.setCertificateTypeEnum(CertificateTypeEnum.valueOf(request.getCertificateType()));
        creditApplyOrder.setStatus(ApplyStatus.INIT);
        //todo UUID工具
        creditApplyOrder.setApplyOrderId("");
        return creditApplyOrder;
    }
}
