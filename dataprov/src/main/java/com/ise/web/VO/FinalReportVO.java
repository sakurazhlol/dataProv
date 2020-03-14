package com.ise.web.VO;

import lombok.Data;

import java.util.List;

@Data
public class FinalReportVO extends BasicObject{
    private List<BugReportVO> bugReportList;
    private String reportMixer;
}
