package com.ise.web.VO;

import lombok.Data;

@Data
public class ReportReviewVO extends BasicObject{
    private String bugReportID;
    private String bugReportScore;
    private String viewerId;
    private String viewerName;
}
