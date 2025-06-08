package com.loan.approve.service;

import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.repository.mapper.ApplyOrderMapper;
import com.loan.approve.repository.dao.ApprovalOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CreditApplyService {

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    public String addNewApply(CreditApplyOrder creditApplyOrder) {
        ApprovalOrderDAO approvalOrderDAO = ApprovalOrderDAO.fromDO(creditApplyOrder);
        applyOrderMapper.insertApplyOrder(approvalOrderDAO);
        return approvalOrderDAO.getApplyOrderId();
    }

    public CreditApplyOrder queryApprovalInfo(String username, String certificateType, String certificateNo) {
        List<ApprovalOrderDAO> approvalOrderDAOList = applyOrderMapper.selectByUserCertificate(username, certificateType, certificateNo);
        //get the newest record. And make sure there is only one valid approval order at one time in other way.
        Optional<ApprovalOrderDAO> latestOrder = approvalOrderDAOList.stream()
                .max(Comparator.comparing(ApprovalOrderDAO::getCreateTime));

        return ApprovalOrderDAO.toDO(latestOrder.orElse(null));
    }
}
