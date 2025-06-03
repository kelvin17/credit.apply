package com.loan.approve.model;

import com.loan.approve.model.enums.DataItemName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCollectTask {

    String taskId;
    String applyOrderId;
    String userId;
    DataItemName dataItem;

}
