package com.ise.service;

import cn.iselab.bcclient.model.TaskState;
import com.ise.web.VO.TaskCollection;

public interface TaskService {
    String updateTaskStatus(String taskID,String taskName,Integer taskStatus);
    String getTaskStatusBytaskID(String taskId);
    TaskCollection getAllTask();
    TaskState getDetailByTaskId(String taskId);
}
