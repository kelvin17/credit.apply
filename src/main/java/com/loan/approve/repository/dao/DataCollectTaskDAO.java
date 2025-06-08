package com.loan.approve.repository.dao;

import com.loan.approve.model.DataCollectTask;
import com.loan.approve.model.enums.DataCollectTaskStatus;
import com.loan.approve.model.enums.DataItemName;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DataCollectTaskDAO {

    String taskId;
    String applyOrderId;
    String userName;
    String dataItem;
    String status;
    Timestamp createTime;
    Timestamp modifyTime;

    public static DataCollectTaskDAO fromDO(DataCollectTask taskDO) {
        DataCollectTaskDAO dao = new DataCollectTaskDAO();
        dao.taskId = taskDO.getTaskId();
        dao.applyOrderId = taskDO.getApplyOrderId();
        dao.userName = taskDO.getUserName();
        dao.dataItem = taskDO.getDataItem().getItemName();
        dao.status = taskDO.getStatus().name();
        return dao;
    }

    public static DataCollectTask toDO(DataCollectTaskDAO dao) {
        if (dao == null) {
            return null;
        }
        DataCollectTask dataCollectTask = new DataCollectTask();
        dataCollectTask.setTaskId(dao.taskId);
        dataCollectTask.setApplyOrderId(dao.applyOrderId);
        dataCollectTask.setUserName(dao.userName);
        dataCollectTask.setDataItem(DataItemName.valueOf(dao.dataItem));
        dataCollectTask.setStatus(DataCollectTaskStatus.valueOf(dao.status));
        return dataCollectTask;
    }
}
