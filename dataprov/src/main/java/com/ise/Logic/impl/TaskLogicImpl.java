package com.ise.Logic.impl;

import cn.iselab.bcclient.model.*;
import com.ise.Logic.TaskLogic;
import com.ise.service.FinalRepService;
import com.ise.service.RequireService;
import com.ise.service.TaskService;
import com.ise.service.TestReportService;
import com.ise.web.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskLogicImpl implements TaskLogic {

    @Autowired
    TaskService taskService;

    @Autowired
    RequireService requireService;

    @Autowired
    TestReportService testReportService;

    @Autowired
    FinalRepService finalRepService;

    @Override
    public TaskCollection getAllTask() {
        return this.taskService.getAllTask();
    }

    @Override
    public TaskVO getDetailByTaskId(String taskId) {
        TaskVO result = new TaskVO();
        TaskState taskState = this.taskService.getDetailByTaskId(taskId);
        result.setTaskId(taskState.getTaskId());
        result.setTaskName(taskState.getTaskName());
        if(taskState.getTaskState() == 0){
            result.setTaskStatus("需求提交阶段");
        }else if(taskState.getTaskState() == 1){
            result.setTaskStatus("需求审核阶段");
        }else if(taskState.getTaskState() == 2){
            result.setTaskStatus("报告提交阶段");
        }else if(taskState.getTaskState() == 3){
            result.setTaskStatus("报告审核阶段");
        }else if(taskState.getTaskState() == 4){
            result.setTaskStatus("报告融合阶段");
        }
        Long releaseTime = this.requireService.getReqReviewByTaskId(taskId).getUpdateTime();
        result.setUpdateTime(releaseTime);
        return result;
    }

    @Override
    public ReqVO getReqByTaskId(String taskId) {
        ReqVO result = new ReqVO();
        RequestCommit requestCommit = requireService.getReqCommitByTaskId(taskId);
        result.setRe_submit_date(requestCommit.getUpdateTime());
        result.setRe_doc_name(requestCommit.getRequestDocName());
        result.setRequester(requestCommit.getRequester());
        result.setTest_app_name(requestCommit.getTestSoftwareName());
        return result;
    }

    @Override
    public ReqReVO getReqReByTaskId(String taskId) {
        ReqReVO result = new ReqReVO();
        RequestReview requestReview = requireService.getReqReviewByTaskId(taskId);
        result.setCheck_date(requestReview.getUpdateTime());
        result.setCheck_result(requestReview.getReviewResult());
        result.setChecker(requestReview.getRequestReviewer());
        return result;
    }

    @Override
    public ReportCollection getReportByTaskId(String taskId) {
        ReportCollection result = new ReportCollection();
        List<RepVO> repVOS = new ArrayList<>();
        List<TestReport> testReportList = this.testReportService.getTestRepByTaskId(taskId);
        testReportList.forEach(v->{
            RepVO repVO = new RepVO();
            repVO.setReport(v.getTestReportName());
            repVO.setWorker(v.getWorkerName());
            repVO.setCommitDate(v.getUpdateTime());
            repVOS.add(repVO);
        });
        result.setRepVOS(repVOS);
        return result;
    }

    @Override
    public RepReCollection getRepReviewByTaskId(String taskId) {
        RepReCollection result = new RepReCollection();
        List<RepReVO> repReVOS = new ArrayList<>();
        List<ReportReview> reportReviewList = this.testReportService.getRepReviewByTaskId(taskId);
        reportReviewList.forEach(v->{
            RepReVO repReVO = new RepReVO();
            repReVO.setRepName(v.getBugReportId());
            repReVO.setRepScore(v.getBugReportScore());
            repReVO.setReviewerName(v.getReportReviewer());
            repReVO.setReviewTime(v.getUpdateTime());
            repReVOS.add(repReVO);
        });
        result.setRepReVOList(repReVOS);
        return result;
    }

    @Override
    public ReportMixVO getRepMixByTaskId(String taskId) {
        ReportMixVO result = new ReportMixVO();
        ReportMix reportMix = this.finalRepService.getFinalRepByTaskID(taskId);
        result.setIntegrator(reportMix.getReportMixer());
        result.setIntegrate_time(reportMix.getUpdateTime());
        return result;
    }

    @Override
    public TestRepCollection getAllrepByTaskId(String taskId) {
        TestRepCollection result = new TestRepCollection();
        List<TestReportVO> testReportVOS = new ArrayList<>();
        List<TestReport> testReports = this.testReportService.getTestRepByTaskId(taskId);
        testReports.forEach(v->{
            TestReportVO testReportVO = new TestReportVO();
            testReportVO.setTaskId(v.getTaskId());
            testReportVO.setTaskName(v.getTaskName());
            testReportVO.setReportName(v.getTestReportName());
            testReportVO.setReportHash(v.getReportHash());
            testReportVO.setUpdateTime(v.getUpdateTime());
            List<String> bugList = v.getBugReportList();
            List<BugRepScoreVO> bugRepScoreVOS = new ArrayList<>();
            bugList.forEach(w->{
                BugRepScoreVO bugRepScoreVO = testReportService.getBugVOBybugId(w);
                if(bugRepScoreVO.getBugID()==null){
                    bugRepScoreVO.setBugID(w);
                }
                bugRepScoreVOS.add(bugRepScoreVO);
            });
            testReportVO.setBugReport(bugRepScoreVOS);
            testReportVOS.add(testReportVO);
        });
        result.setTestReportVOS(testReportVOS);
        return result;
    }


}
