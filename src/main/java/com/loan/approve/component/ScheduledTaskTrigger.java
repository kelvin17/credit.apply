package com.loan.approve.component;

import com.loan.approve.model.enums.ApplyStatus;
import com.loan.approve.repository.dao.ApprovalOrderDAO;
import com.loan.approve.repository.mapper.ApplyOrderMapper;
import com.loan.approve.service.ApprovalTaskWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTaskTrigger {

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    @Autowired
    private ApprovalTaskWorker approvalTaskWorker;

    @Scheduled(fixedRate = 60_000)
    public void pushApprovalStatusFlowing() {
        System.out.println("ScheduledTaskTrigger work at: " + System.currentTimeMillis());

        List<String> unFinishedStatusList = new ArrayList<>();
        for (ApplyStatus status : ApplyStatus.values()) {
            if (!status.isEnd) {
                unFinishedStatusList.add(status.current);
            }
        }
        List<ApprovalOrderDAO> daoList = applyOrderMapper.selectUnFinishedOrder(unFinishedStatusList);
        if (daoList.isEmpty()) {
            System.out.println("No approval order found");
        } else {
            daoList.forEach(dao -> {
                approvalTaskWorker.handlerOrder(ApprovalOrderDAO.toDO(dao));
            });
        }
    }
}
