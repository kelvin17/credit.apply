package com.loan.approve.repository.mapper;

import com.loan.approve.repository.dao.UserCreditAccountDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCreditAccountMapper {

    int insertOrUpdateUserCreditAccount(UserCreditAccountDAO dao);

    UserCreditAccountDAO selectByUserCertificate(
            @Param("userName") String userId,
            @Param("certificateType") String certificateType,
            @Param("certificateID") String certificateID
    );

}
