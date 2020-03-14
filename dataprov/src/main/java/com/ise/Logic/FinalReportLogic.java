package com.ise.Logic;

import com.ise.web.VO.FinalRepVO;
import com.ise.web.VO.FinalReportVO;

public interface FinalReportLogic {
     FinalReportVO uploadFinalReport(FinalReportVO finalReportVO);
     FinalRepVO getFinalReport(String taskID);
}
