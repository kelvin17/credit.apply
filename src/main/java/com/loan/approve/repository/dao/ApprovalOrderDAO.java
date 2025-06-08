package com.loan.approve.repository.dao;

import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.model.enums.ApplyStatus;
import com.loan.approve.model.enums.CertificateTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApprovalOrderDAO {

    private String applyOrderId;
    private String userName;
    private String status;
    private String certificateType;
    private String certificateId;
    private String phoneNumber;
    private String email;
    private Timestamp createTime;
    private Timestamp modifyTime;

    public static ApprovalOrderDAO fromDO(CreditApplyOrder applyOrder) {
        ApprovalOrderDAO approvalOrderDAO = new ApprovalOrderDAO();
        approvalOrderDAO.setApplyOrderId(applyOrder.getApplyOrderId());
        approvalOrderDAO.setUserName(applyOrder.getUserName());
        approvalOrderDAO.setStatus(applyOrder.getStatus().name());
        approvalOrderDAO.setCertificateType(applyOrder.getCertificateTypeEnum().name());
        approvalOrderDAO.setCertificateId(applyOrder.getCertificateId());
        approvalOrderDAO.setPhoneNumber(applyOrder.getPhoneNumber());
        approvalOrderDAO.setEmail(applyOrder.getEmail());
        approvalOrderDAO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        approvalOrderDAO.setModifyTime(new Timestamp(System.currentTimeMillis()));
        return approvalOrderDAO;
    }

    public static CreditApplyOrder toDO(ApprovalOrderDAO dao) {
        if (dao == null) {
            return null;
        }
        CreditApplyOrder applyOrder = new CreditApplyOrder();
        applyOrder.setApplyOrderId(dao.getApplyOrderId());
        applyOrder.setUserName(dao.getUserName());
        applyOrder.setCertificateTypeEnum(CertificateTypeEnum.valueOf(dao.getCertificateType()));
        applyOrder.setCertificateId(dao.getCertificateId());
        applyOrder.setPhoneNumber(dao.getPhoneNumber());
        applyOrder.setStatus(ApplyStatus.valueOf(dao.getStatus()));
        applyOrder.setEmail(dao.getEmail());
        return applyOrder;
    }
}
