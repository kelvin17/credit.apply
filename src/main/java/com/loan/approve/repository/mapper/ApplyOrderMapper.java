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
            @Param("certificateId") String certificateID
    );

    List<ApprovalOrderDAO> selectUnFinishedOrder(@Param("statuses") List<String> unFinishedStatusList);

    ApprovalOrderDAO selectByOrderIdForUpdate(@Param("applyOrderId") String applyOrderId);

    int updateStatus(@Param("status") String status, @Param("applyOrderId") String applyOrderId,
                     @Param("oldStatus") String oldStatus);

    List<ApprovalOrderDAO> selectByPhoneNumber(
            @Param("phoneNumber") String phoneNumber
    );

    List<ApprovalOrderDAO> selectByEmail(
            @Param("email") String email
    );


}
