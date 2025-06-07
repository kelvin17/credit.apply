package com.loan.approve.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCreditAccountMapper {

    int insertOrUpdateUserCreditAccount(UserCreditAccountDAO dao);

    UserCreditAccountDAO selectByUserCertificate(
            @Param("userName") String userId,
            @Param("certificateType") String certificateType,
            @Param("certificateID") String certificateID
    );

}
