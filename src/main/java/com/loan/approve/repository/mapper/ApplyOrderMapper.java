package com.loan.approve.repository.mapper;

import com.loan.approve.repository.dao.ApprovalOrderDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyOrderMapper {

    int insertApplyOrder(ApprovalOrderDAO orderDAO);

    List<ApprovalOrderDAO> selectByUserCertificate(
            @Param("userName") String userName,
            @Param("certificateType") String certificateType,
            @Param("certificateID") String certificateID
    );

    List<ApprovalOrderDAO> selectByPhoneNumber(
            @Param("phoneNumber") String phoneNumber
    );

    List<ApprovalOrderDAO> selectByEmail(
            @Param("email") String email
    );


}
