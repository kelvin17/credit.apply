package com.loan.approve.service;

import com.loan.approve.model.UserCreditAccount;
import org.springframework.stereotype.Service;

@Service
public class CreditManageService {

    public UserCreditAccount queryUserCreditAccount(String username, String certificateNo, String certificateType) {
        UserCreditAccount userCreditAccount = new UserCreditAccount();
        //todo query from db
        return userCreditAccount;
    }

}
