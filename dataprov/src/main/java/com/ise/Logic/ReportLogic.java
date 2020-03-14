package com.ise.Logic;

import cn.iselab.bcclient.model.ReportReview;
import cn.iselab.bcclient.model.TestReport;
import com.ise.web.VO.ReportReviewVO;
import com.ise.web.VO.TestRepCollection;
import com.ise.web.VO.TestReportVO;

public interface ReportLogic {
    TestReport uploadTestReport(TestReport testReport);
    ReportReview uploadRepReview(ReportReview reportReview);
    TestReportVO getTestReport(String repId);
    ReportReviewVO getRepReview(String repId);
    TestRepCollection getTestReportByUsrID(String usrId);
}
