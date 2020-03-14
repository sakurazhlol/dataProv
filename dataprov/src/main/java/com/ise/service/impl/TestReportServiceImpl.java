package com.ise.service.impl;

import cn.iselab.bcclient.model.ReportReview;
import cn.iselab.bcclient.model.TestReport;
import com.ise.dao.DocDao;
import com.ise.service.IdService;
import com.ise.service.TestReportService;
import com.ise.web.VO.BugRepScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestReportServiceImpl implements TestReportService {

    @Autowired
    private IdService idService;

    @Autowired
    private DocDao docDao;

    @Override
    public TestReport uploadTestReport(TestReport testReport) {
        String docId = this.idService.genId();
        this.docDao.createDoc(docId,testReport);
        return testReport;
    }

    @Override
    public ReportReview uploadRepReview(ReportReview reportReview) {
        String docId = this.idService.genId();
        this.docDao.createDoc(docId,reportReview);
        return reportReview;
    }

    @Override
    public BugRepScoreVO getBugVOBybugId(String bugId) {
        BugRepScoreVO result = new BugRepScoreVO();
        List<ReportReview> reportReviews = new ArrayList<>();
        this.docDao.getDocByKV("bugReportId",bugId,ReportReview.class).forEach(v->{
            reportReviews.add((ReportReview)v);
        });
        if(reportReviews.isEmpty()){
            return result;
        }
        result.setBugScore(reportReviews.get(0).getBugReportScore().toString());
        result.setBugReviewer(reportReviews.get(0).getReportReviewer());
        String reportId = reportReviews.get(0).getTestReportId();
        result.setWorker(this.getWorkerByRepID(reportId));
        result.setBugID(bugId);
        return result;
    }

    @Override
    public String getRepNameByRepId(String repId) {
        List<TestReport> testReports = new ArrayList<>();
        this.docDao.getDocByKV("testReportId",repId,TestReport.class).forEach(v->{
            testReports.add((TestReport)v);
        });
        return testReports.get(0).getTestReportName();
    }

    @Override
    public String getWorkerByRepID(String repId) {
        List<TestReport> testReports = new ArrayList<>();
        this.docDao.getDocByKV("testReportId",repId,TestReport.class).forEach(v->{
            testReports.add((TestReport)v);
        });
        return testReports.get(0).getWorkerName();
    }

    @Override
    public List<TestReport> getTestRepByUserID(String usrId) {
        List<TestReport> result = new ArrayList<>();
        this.docDao.getDocByKV("workerId",usrId,TestReport.class).forEach(v->{
            result.add((TestReport)v);
        });
        return result;
    }

    @Override
    public List<TestReport> getTestRepByTaskId(String taskId) {
        List<TestReport> result = new ArrayList<>();
        this.docDao.getDocByKV("taskId",taskId,TestReport.class).forEach(v->{
            result.add((TestReport)v);
        });
        return result;
    }

    @Override
    public List<ReportReview> getRepReviewByTaskId(String taskId) {
        List<ReportReview> result = new ArrayList<>();
        this.docDao.getDocByKV("taskId",taskId,ReportReview.class).forEach(v->{
            result.add((ReportReview)v);
        });
        return result;
    }


}
