package com.ise.web.VO;

import lombok.Data;

@Data
public class ReqReviewVO extends BasicObject {
    private String reqId;
    private String reviewerID;
    private String reviewResult;
    private String reviewName;
}
