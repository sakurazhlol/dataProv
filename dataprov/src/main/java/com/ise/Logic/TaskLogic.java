package com.ise.Logic;

import cn.iselab.bcclient.model.ReportMix;
import com.ise.web.VO.*;

public interface TaskLogic {
    TaskCollection getAllTask();
    TaskVO getDetailByTaskId(String taskId);
    ReqVO getReqByTaskId(String taskId);
    ReqReVO getReqReByTaskId(String taskId);
    ReportCollection getReportByTaskId(String taskId);
    RepReCollection getRepReviewByTaskId(String taskId);
    ReportMixVO getRepMixByTaskId(String taskId);
    TestRepCollection getAllrepByTaskId(String taskid);
}
