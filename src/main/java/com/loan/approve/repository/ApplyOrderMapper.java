package com.loan.approve.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyOrderMapper {

    void insertApplyOrder(ApplyOrderDAO orderDAO);

    List<ApplyOrderDAO> selectByUserCertificate(
            @Param("userName") String userName,
            @Param("certificateType") String certificateType,
            @Param("certificateID") String certificateID
    );

    List<ApplyOrderDAO> selectByPhoneNumber(
            @Param("phoneNumber") String phoneNumber
    );

    List<ApplyOrderDAO> selectByEmail(
            @Param("email") String email
    );


}
