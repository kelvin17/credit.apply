package com.loan.approve.service;

import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.repository.ApplyOrderDAO;
import com.loan.approve.repository.ApplyOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CreditApplyService {

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    public String addNewApply(CreditApplyOrder creditApplyOrder) {
        ApplyOrderDAO applyOrderDAO = ApplyOrderDAO.fromDO(creditApplyOrder);
        applyOrderMapper.insertApplyOrder(applyOrderDAO);
        return applyOrderDAO.getApplyOrderId();
    }

    public CreditApplyOrder queryApprovalInfo(String username, String certificateNo, String certificateType) {
        List<ApplyOrderDAO> applyOrderDAOList = applyOrderMapper.selectByUserCertificate(username, certificateNo, certificateType);
        if (CollectionUtils.isEmpty(applyOrderDAOList)) {
            return null;
        }
        //todo sort by time
//        applyOrderDAOList.sort(k->k.getCreateTime());
        return ApplyOrderDAO.toDO(applyOrderDAOList.get(0));
    }
}
