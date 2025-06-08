package com.loan.approve.repository.dao;

import com.loan.approve.model.UserCreditAccount;
import com.loan.approve.model.enums.CertificateTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserCreditAccountDAO {
    private String userName;
    private String certificateType;
    private String certificateId;
    private boolean hasCreditQuota;
    private Date validDateBegin;
    private Date validDateEnd;
    private double quotaAmount;
    private double usedAmount;
    private String currency;

    public static UserCreditAccount toDO(UserCreditAccountDAO userCreditAccountDAO) {
        if (userCreditAccountDAO == null) {
            return null;
        }
        UserCreditAccount userCreditAccount = new UserCreditAccount();

        userCreditAccount.setUserName(userCreditAccountDAO.getUserName());
        userCreditAccount.setCertificateTypeEnum(CertificateTypeEnum.valueOf(userCreditAccountDAO.getCertificateType()));
        userCreditAccount.setCertificateId(userCreditAccountDAO.getCertificateId());
        userCreditAccount.setHasCreditQuota(userCreditAccountDAO.isHasCreditQuota());
        userCreditAccount.setValidDateBegin(userCreditAccountDAO.getValidDateBegin());
        userCreditAccount.setValidDateEnd(userCreditAccountDAO.getValidDateEnd());
        userCreditAccount.setQuotaAmount(userCreditAccountDAO.getQuotaAmount());
        userCreditAccount.setUsedAmount(userCreditAccountDAO.getUsedAmount());
        userCreditAccount.setCurrency(userCreditAccountDAO.getCurrency());

        return userCreditAccount;

    }
}
