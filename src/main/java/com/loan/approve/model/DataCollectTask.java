package com.loan.approve.model;

import com.loan.approve.model.enums.DataCollectTaskStatus;
import com.loan.approve.model.enums.DataItemName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCollectTask {

    String taskId;
    String applyOrderId;
    String userName;
    DataItemName dataItem;
    DataCollectTaskStatus status;
    Timestamp createTime;
    Timestamp modifyTime;

}
