package com.loan.approve.repository.mapper;

import com.loan.approve.repository.dao.DataCollectTaskDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataCollectTaskMapper {

    int insertDataCollectTask(DataCollectTaskDAO collectTaskDAO);

    List<DataCollectTaskDAO> selectUnFinishedByApplyOrderId(
            @Param("applyOrderId") String applyOrderId,
            @Param("status") String status);

    int updateStatus(@Param("taskId") String taskId,
                     @Param("status") String status,
                     @Param("applyOrderId") String applyOrderId,
                     @Param("oldStatus") String oldStatus);


}
