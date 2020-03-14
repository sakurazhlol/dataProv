package com.ise.web.controller;

import com.ise.Logic.TaskLogic;
import com.ise.web.VO.*;
import com.ise.web.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Require Doc")
@RestController
public class TaskController {

    @Autowired
    private TaskLogic taskLogic;

    @ApiOperation(value = "/platform", notes = "查看所有任务")
    @RequestMapping(value = "/trace/", method = RequestMethod.GET)
    public ResponseResult<TaskCollection> getAllTask(){
        TaskCollection result = taskLogic.getAllTask();
        return new ResponseResult<TaskCollection>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "查看任务基本信息")
    @RequestMapping(value = "/getDetail/{taskId}", method = RequestMethod.GET)
    public ResponseResult<TaskVO> getTaskDetail(@PathVariable String taskId){
        TaskVO result = taskLogic.getDetailByTaskId(taskId);
        return new ResponseResult<TaskVO>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "任务需求提交信息")
    @RequestMapping(value = "/getReqCommit/{taskId}", method = RequestMethod.GET)
    public ResponseResult<ReqVO> getReqByTaskId(@PathVariable String taskId){
        ReqVO result = taskLogic.getReqByTaskId(taskId);
        return new ResponseResult<ReqVO>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "任务需求审查信息")
    @RequestMapping(value = "/getReqReview/{taskId}", method = RequestMethod.GET)
    public ResponseResult<ReqReVO> getReqReviewByTaskId(@PathVariable String taskId){
        ReqReVO result = taskLogic.getReqReByTaskId(taskId);
        return new ResponseResult<ReqReVO>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "报告提交序列")
    @RequestMapping(value = "/getReportByTaskId/{taskId}", method = RequestMethod.GET)
    public ResponseResult<ReportCollection> getReportByTaskId(@PathVariable String taskId){
        ReportCollection result = taskLogic.getReportByTaskId(taskId);
        return new ResponseResult<ReportCollection>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "报告审核序列")
    @RequestMapping(value = "/getRepReviewByTaskId/{taskId}", method = RequestMethod.GET)
    public ResponseResult<RepReCollection> getRepReviewByTaskId(@PathVariable String taskId){
        RepReCollection result = taskLogic.getRepReviewByTaskId(taskId);
        return new ResponseResult<RepReCollection>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "报告融合信息")
    @RequestMapping(value = "/getRepMixByTaskId/{taskId}", method = RequestMethod.GET)
    public ResponseResult<ReportMixVO> getRepMixByTaskId(@PathVariable String taskId){
        ReportMixVO result = taskLogic.getRepMixByTaskId(taskId);
        return new ResponseResult<ReportMixVO>().setData(result);
    }

    @ApiOperation(value = "/platform", notes = "报告提交阶段详细信息")
    @RequestMapping(value = "/getAllrepByTaskId/{taskId}", method = RequestMethod.GET)
    public ResponseResult<TestRepCollection> getAllrepByTaskId(@PathVariable String taskId){
        TestRepCollection result = taskLogic.getAllrepByTaskId(taskId);
        return new ResponseResult<TestRepCollection>().setData(result);
    }
}
