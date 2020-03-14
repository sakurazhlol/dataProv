package com.ise.Logic.impl;

import cn.iselab.bcclient.model.ReportMix;
import com.ise.Logic.FinalReportLogic;
import com.ise.service.FinalRepService;
import com.ise.service.TaskService;
import com.ise.service.TestReportService;
import com.ise.web.VO.BugRepScoreVO;
import com.ise.web.VO.BugReportVO;
import com.ise.web.VO.FinalRepVO;
import com.ise.web.VO.FinalReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinalReportImpl implements FinalReportLogic {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FinalRepService finalRepService;

    @Autowired
    private TestReportService testReportService;

    @Override
    public FinalReportVO uploadFinalReport(FinalReportVO finalReportVO) {
        this.taskService.updateTaskStatus(finalReportVO.getTaskId(),finalReportVO.getTaskName(),4);
        ReportMix reportMix = new ReportMix();
        reportMix.setTaskId(finalReportVO.getTaskId());
        reportMix.setTaskName(finalReportVO.getTaskName());
        reportMix.setReportMixer(finalReportVO.getReportMixer());
        reportMix.setUpdateTime(finalReportVO.getUpdateTime());
        List<BugReportVO> bugReportVOList = finalReportVO.getBugReportList();
        List<String> bugRep = new ArrayList<>();
        bugReportVOList.forEach(v->{
            bugRep.add(v.getBugReportID());
        });
        reportMix.setBugReportList(bugRep);
        this.finalRepService.uploadFinalRep(reportMix);
        return finalReportVO;
    }

    @Override
    public FinalRepVO getFinalReport(String taskId) {
        FinalRepVO result = new FinalRepVO();
        List<BugRepScoreVO> bugRepScoreVOS = new ArrayList<>();
        ReportMix reportMix = this.finalRepService.getFinalRepByTaskID(taskId);
        List<String> bugList = reportMix.getBugReportList();
        bugList.forEach(v->{
            BugRepScoreVO bugRepScoreVO = testReportService.getBugVOBybugId(v);
            bugRepScoreVOS.add(bugRepScoreVO);
        });
        result.setReportMixer(reportMix.getReportMixer());
        result.setBugRepScoreVOList(bugRepScoreVOS);
        result.setReportName("最终交付报告");
        result.setReportHash(reportMix.getReportHash());
        return result;
    }
}
