package com.ise.service;

import cn.iselab.bcclient.model.ReportMix;
import com.ise.web.VO.FinalReportVO;

public interface FinalRepService {
    ReportMix uploadFinalRep(ReportMix reportMix);
    ReportMix getFinalRepByTaskID(String taskID);
}
