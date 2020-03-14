package com.ise.web.VO;

import lombok.Data;

import java.util.List;
@Data
public class FinalRepVO extends BasicObject{
    private String reportMixer;
    private List<BugRepScoreVO> bugRepScoreVOList;
    private String reportName;
    private String reportHash;
}
