package com.loan.approve.service;

import com.loan.approve.model.UserCreditAccount;
import com.loan.approve.repository.dao.UserCreditAccountDAO;
import com.loan.approve.repository.mapper.UserCreditAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditManageService {

    @Autowired
    private UserCreditAccountMapper userCreditAccountMapper;

    public UserCreditAccount queryUserCreditAccount(String username, String certificateNo, String certificateType) {
        UserCreditAccount userCreditAccount = new UserCreditAccount();
        UserCreditAccountDAO dao = userCreditAccountMapper.selectByUserCertificate(username, certificateNo, certificateType);
        if (dao != null) {

        }
        //todo query from db
        return userCreditAccount;
    }

}
