package com.ise.service;

import cn.iselab.bcclient.model.ReportReview;
import cn.iselab.bcclient.model.TestReport;
import com.ise.web.VO.BugRepScoreVO;

import java.util.List;

public interface TestReportService {
    TestReport uploadTestReport(TestReport testReport);
    ReportReview uploadRepReview(ReportReview reportReview);
    BugRepScoreVO getBugVOBybugId(String bugId);
    String getRepNameByRepId(String repId);
    String getWorkerByRepID(String repId);
    List<TestReport> getTestRepByUserID(String usrId);
    List<TestReport> getTestRepByTaskId(String taskId);
    List<ReportReview> getRepReviewByTaskId(String taskId);
}
