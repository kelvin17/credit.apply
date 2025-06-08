package com.loan.approve.service;

import com.loan.approve.model.CreditApplyOrder;
import com.loan.approve.model.DataCollectTask;
import com.loan.approve.model.UserCreditAccount;
import com.loan.approve.model.enums.ApplyStatus;
import com.loan.approve.model.enums.DataCollectTaskStatus;
import com.loan.approve.model.enums.DataItemName;
import com.loan.approve.repository.dao.ApprovalOrderDAO;
import com.loan.approve.repository.dao.DataCollectTaskDAO;
import com.loan.approve.repository.dao.UserCreditAccountDAO;
import com.loan.approve.repository.mapper.ApplyOrderMapper;
import com.loan.approve.repository.mapper.DataCollectTaskMapper;
import com.loan.approve.repository.mapper.UserCreditAccountMapper;
import com.loan.approve.utils.OrderIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ApprovalTaskWorker {

    @Autowired
    private ApplyOrderMapper applyOrderMapper;

    @Autowired
    private DataCollectTaskMapper dataCollectTaskMapper;

    @Autowired
    private UserCreditAccountMapper userCreditAccountMapper;

    @Async("statusFlowingExecutor")
    @Transactional
    public void handlerOrder(CreditApplyOrder creditApplyOrder) {

        ApplyStatus status = creditApplyOrder.getStatus();
        switch (status) {
            case INIT -> handleInit(creditApplyOrder);
            case INVESTIGATE -> handleInvestigate(creditApplyOrder);
            case APPROVING -> handleApproval(creditApplyOrder);
        }
    }

    public void handleInit(CreditApplyOrder creditApplyOrder) {
        //1. first lock, second check, third execute
        ApprovalOrderDAO approvalOrderDAO = applyOrderMapper.selectByOrderIdForUpdate(creditApplyOrder.getApplyOrderId());
        if (StringUtils.equals(approvalOrderDAO.getStatus(), creditApplyOrder.getStatus().name())) {
            //2. create the investigate subtask and update the status
            for (DataItemName dataItemName : DataItemName.values()) {
                DataCollectTask dataCollectTask = new DataCollectTask();
                dataCollectTask.setApplyOrderId(creditApplyOrder.getApplyOrderId());
                dataCollectTask.setTaskId(OrderIdGenerator.generate(creditApplyOrder.getUserName(), OrderIdGenerator.DATA_COLLECT + dataItemName.getItemName()));
                dataCollectTask.setDataItem(dataItemName);
                dataCollectTask.setUserName(creditApplyOrder.getUserName());
                dataCollectTask.setStatus(DataCollectTaskStatus.INIT);
                dataCollectTaskMapper.insertDataCollectTask(DataCollectTaskDAO.fromDO(dataCollectTask));
            }
            applyOrderMapper.updateStatus(creditApplyOrder.getStatus().next, creditApplyOrder.getApplyOrderId(), creditApplyOrder.getStatus().current);
        }
    }

    public void handleInvestigate(CreditApplyOrder creditApplyOrder) {
        //1. first lock, second check, third execute
        ApprovalOrderDAO approvalOrderDAO = applyOrderMapper.selectByOrderIdForUpdate(creditApplyOrder.getApplyOrderId());
        if (StringUtils.equals(approvalOrderDAO.getStatus(), creditApplyOrder.getStatus().name())) {
            //2. check the subTask, update the status if all subtasks finish
            List<DataCollectTaskDAO> unFinished = dataCollectTaskMapper.selectUnFinishedByApplyOrderId(creditApplyOrder.getApplyOrderId(), DataCollectTaskStatus.INIT.name());
            if (unFinished.isEmpty()) {
                applyOrderMapper.updateStatus(creditApplyOrder.getStatus().next, creditApplyOrder.getApplyOrderId(), creditApplyOrder.getStatus().current);
            } else {
                //check task expired or not
                unFinished.forEach(dataCollectTaskDAO -> {
                    DataItemName dataItemName = DataItemName.valueOf(dataCollectTaskDAO.getDataItem());
                    Date expiredDate = DateUtils.addDays(dataCollectTaskDAO.getCreateTime(), dataItemName.getTaskExpireDays());
                    if (expiredDate.before(new Date())) {
                        dataCollectTaskMapper.updateStatus(dataCollectTaskDAO.getTaskId(), DataCollectTaskStatus.TASK_EXPIRED.name(), dataCollectTaskDAO.getApplyOrderId(), DataCollectTaskStatus.INIT.name());
                    }
                });
            }
        }
    }

    public void handleApproval(CreditApplyOrder creditApplyOrder) {
        //1. first lock, second check, third execute
        ApprovalOrderDAO approvalOrderDAO = applyOrderMapper.selectByOrderIdForUpdate(creditApplyOrder.getApplyOrderId());
        if (StringUtils.equals(approvalOrderDAO.getStatus(), creditApplyOrder.getStatus().name())) {
            //2. invoke the approval service - mock - todo add a server
            boolean finishApproval = true;
            UserCreditAccount userCreditAccount = mockResult(creditApplyOrder, finishApproval);
            UserCreditAccountDAO userCreditAccountDAO = UserCreditAccountDAO.fromDO(userCreditAccount);
            userCreditAccountMapper.insertOrUpdateUserCreditAccount(userCreditAccountDAO);

            if (finishApproval) {
                applyOrderMapper.updateStatus(creditApplyOrder.getStatus().next, creditApplyOrder.getApplyOrderId(), creditApplyOrder.getStatus().current);
            }
        }
    }

    private UserCreditAccount mockResult(CreditApplyOrder creditApplyOrder, boolean mockApproval) {
        UserCreditAccount userCreditAccount = new UserCreditAccount();
        userCreditAccount.setUserName(creditApplyOrder.getUserName());
        userCreditAccount.setCertificateTypeEnum(creditApplyOrder.getCertificateTypeEnum());
        userCreditAccount.setCertificateId(creditApplyOrder.getCertificateId());
        userCreditAccount.setHasCreditQuota(mockApproval);

        if (mockApproval) {
            CurrencyUnit currency = Monetary.getCurrency("DKK");
            MonetaryAmount amount = Money.of(10000.00, currency);

            userCreditAccount.setCurrency(currency.toString());
            userCreditAccount.setQuotaAmount(amount.getNumber().numberValueExact(BigDecimal.class));
            userCreditAccount.setUsedAmount(BigDecimal.ZERO);
        }

        userCreditAccount.setValidDateBegin(new Date());
        userCreditAccount.setValidDateEnd(DateUtils.addDays(new Date(), 365));
        return userCreditAccount;
    }
}
