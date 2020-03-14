package com.ise.web.VO;

import lombok.Data;

import java.util.List;

@Data
public class TestReportVO extends BasicObject  {
    private String reportName;
    private String reportStatus;
    private String reportScore;
    private List<BugRepScoreVO> bugReport;
    private String reportHash;
    private String workerId;
    private String workerName;
}
