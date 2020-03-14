package com.ise.Logic.impl;

import cn.iselab.bcclient.model.ReportReview;
import cn.iselab.bcclient.model.TestReport;
import com.ise.Logic.ReportLogic;
import com.ise.service.TaskService;
import com.ise.service.TestReportService;
import com.ise.web.VO.BugRepScoreVO;
import com.ise.web.VO.ReportReviewVO;
import com.ise.web.VO.TestRepCollection;
import com.ise.web.VO.TestReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportLogicImpl implements ReportLogic {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TestReportService testReportService;

    @Override
    public TestReport uploadTestReport(TestReport testReport) {
        String taskId = testReport.getTaskId();
        String status = this.taskService.getTaskStatusBytaskID(taskId);
        if(status.equals("-1")||!status.equals("2")){
            this.taskService.updateTaskStatus(taskId,testReport.getTaskName(),2);
        }
        return this.testReportService.uploadTestReport(testReport);
    }

    @Override
    public ReportReview uploadRepReview(ReportReview reportReview) {
        String taskId = reportReview.getTaskId();
        String status = this.taskService.getTaskStatusBytaskID(taskId);
        if(status.equals("-1")||!status.equals("3")){
            this.taskService.updateTaskStatus(taskId,reportReview.getTaskName(),3);
        }
        return this.testReportService.uploadRepReview(reportReview);

    }

    @Override
    public TestReportVO getTestReport(String repId) {
        return null;
    }

    @Override
    public ReportReviewVO getRepReview(String repId) {
        return null;
    }

    @Override
    public TestRepCollection getTestReportByUsrID(String usrId) {
        TestRepCollection result = new TestRepCollection();
        List<TestReportVO> testReportVOS = new ArrayList<>();
        List<TestReport> testReports = this.testReportService.getTestRepByUserID(usrId);
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
