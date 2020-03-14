package com.ise.Logic.impl;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;
import com.ise.Logic.RequireLogic;
import com.ise.service.RequireService;
import com.ise.service.TaskService;
import com.ise.web.VO.ReqDataCollection;
import com.ise.web.VO.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequireLogicImpl implements RequireLogic {

    @Autowired
    private RequireService requireService;
    @Autowired
    private TaskService taskService;

    @Override
    public RequestCommit uploadRequire(RequestCommit requestCommit) {
        taskService.updateTaskStatus(requestCommit.getTaskId(),requestCommit.getTaskName(),0);
        return this.requireService.uploadRequire(requestCommit);
    }

    @Override
    public RequestReview uploadReqReview(RequestReview requestReview) {
        taskService.updateTaskStatus(requestReview.getTaskId(),requestReview.getTaskName(),1);
        return this.requireService.uploadReqReview(requestReview);
    }

    @Override
    public ReqDataCollection getReqDataByUserId(String reqUserId) {
        ReqDataCollection reqDataCollection = new ReqDataCollection();
        List<RequestVO> requestVOList= new ArrayList<>();
        List<RequestCommit> requestCommitList = this.requireService.getReqCommitByReqUserId(reqUserId);
        requestCommitList.forEach(v->{
            String taskID = v.getTaskId();
            String taskStatus = this.taskService.getTaskStatusBytaskID(taskID);
            RequestVO requestVO = new RequestVO();
            requestVO.setTask_name(v.getTaskName());
            requestVO.setDoc_hash(v.getRequestDocHash());
            requestVO.setLast_update(v.getUpdateTime());
            requestVO.setReq_doc(v.getRequestDocName());
            requestVO.setSoftware(v.getTestSoftwareName());
            requestVO.setState(taskStatus);
            requestVOList.add(requestVO);
        });
        reqDataCollection.setReqData(requestVOList);
        return reqDataCollection;
    }



//    @Override
//    public ReqReviewVO uploadReqReviewVO(ReqReviewVO reqReviewVO) {
//        return null;
//    }
//
//    @Override
//    public DemandCommit getRequireByReqId(String reqId) {
//        return requireService.getRequireByReqId(reqId);
//    }
//
//    @Override
//    public ReqReviewVO getReqReview(String reqId) {
//        return null;
//    }
//
//    @Override
//    public TaskStatusVO getStatusById(String taskId) {
//        return null;
//    }
}
