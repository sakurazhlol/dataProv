package com.ise.service.impl;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;
import cn.iselab.bcclient.model.TaskState;
import com.ise.dao.DocDao;
import com.ise.service.TaskService;
import com.ise.web.VO.TaskCollection;
import com.ise.web.VO.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private DocDao docDao;

    @Override
    public String updateTaskStatus(String taskID, String taskName, Integer taskStatus) {
        TaskState taskState = new TaskState();
        taskState.setTaskId(taskID);
        taskState.setTaskName(taskName);
        taskState.setTaskState(taskStatus);
        long time = System.currentTimeMillis();
        taskState.setUpdateTime(time);
        this.docDao.createDoc(taskID,taskState);
        return taskID;
    }

    @Override
    public String getTaskStatusBytaskID(String taskId) {
        TaskState taskState = (TaskState) this.docDao.getLatestDocById(taskId,TaskState.class);

        if(taskState.getTaskState()==null){
            return "-1";
        }
        return taskState.getTaskState().toString();
    }

    @Override
    public TaskCollection getAllTask() {
        TaskCollection result = new TaskCollection();
        List<TaskVO> taskVOS = new ArrayList<>();
        List<TaskState> taskStates = new ArrayList<>();
        this.docDao.getAllDoc(TaskState.class).forEach(v->{
            taskStates.add((TaskState) v);
        });
        taskStates.forEach(v->{
            TaskVO taskVO = new TaskVO();
            taskVO.setTaskId(v.getTaskId());
            taskVO.setTaskName(v.getTaskName());
            taskVO.setUpdateTime(v.getUpdateTime());
            Integer taskState = v.getTaskState();
            String tasksta = "进行中";
            if(taskState == 4){
                tasksta = "已完成";
            }
            taskVO.setTaskStatus(tasksta);
            taskVOS.add(taskVO);
        });
        result.setTaskVOS(taskVOS);
        return result;

    }

    @Override
    public TaskState getDetailByTaskId(String taskId) {
        return (TaskState)this.docDao.getDocByKV("taskId",taskId,TaskState.class).get(0);
    }

}
