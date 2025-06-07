package com.loan.approve.repository;

import com.loan.approve.api.dto.ApprovalRequest;
import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.model.enums.CertificateTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApplyOrderDAO {

    private String applyOrderId;
    private String userId;
    private String userName;
    private String status;
    private String certificateType;
    private String certificateID;
    private String phoneNumber;
    private String email;
    private Timestamp createTime;

    public static ApplyOrderDAO fromDO(CreditApplyOrder applyOrder) {
        ApplyOrderDAO applyOrderDAO = new ApplyOrderDAO();
        //todo UUID生成工具引入
        applyOrderDAO.setApplyOrderId("");
        applyOrderDAO.setUserName(applyOrder.getUserName());
        applyOrderDAO.setUserId(applyOrder.getUserId());
        applyOrderDAO.setStatus(applyOrder.getStatus().name());
        applyOrderDAO.setCertificateType(applyOrderDAO.getCertificateType());
        applyOrderDAO.setCertificateID(applyOrderDAO.getCertificateID());
        applyOrderDAO.setPhoneNumber(applyOrderDAO.getPhoneNumber());
        applyOrderDAO.setEmail(applyOrderDAO.getEmail());
        applyOrderDAO.setCreateTime(applyOrderDAO.getCreateTime());
        return applyOrderDAO;
    }

    public static CreditApplyOrder toDO(ApplyOrderDAO dao) {
        CreditApplyOrder applyOrder = new CreditApplyOrder();
        applyOrder.setApplyOrderId(dao.getApplyOrderId());
        applyOrder.setUserId(dao.getUserId());
        applyOrder.setUserName(dao.getUserName());
        applyOrder.setCertificateTypeEnum(CertificateTypeEnum.valueOf(dao.getCertificateType()));
        applyOrder.setCertificateID(dao.getCertificateID());
        applyOrder.setPhoneNumber(dao.getPhoneNumber());
        applyOrder.setEmail(dao.getEmail());
        return applyOrder;
    }
}
