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

    public UserCreditAccount queryUserCreditAccount(String username, String certificateType, String certificateNo) {
        UserCreditAccountDAO dao = userCreditAccountMapper.selectByUserCertificate(username, certificateType, certificateNo);
        return UserCreditAccountDAO.toDO(dao);
    }

}
