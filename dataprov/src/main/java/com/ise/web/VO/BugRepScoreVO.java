package com.ise.web.VO;

import lombok.Data;

@Data
public class BugRepScoreVO {
    private String bugID;
    private String bugScore;
    private String worker;
    private String bugReviewer;
}
