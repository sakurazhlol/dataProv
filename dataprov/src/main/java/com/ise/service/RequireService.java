package com.ise.service;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;


import java.util.List;

public interface RequireService {
    RequestCommit uploadRequire(RequestCommit requestCommit);
    RequestReview uploadReqReview(RequestReview requestReview);
    List<RequestCommit> getReqCommitByReqUserId(String reqUsrID);
    RequestReview getReqReviewByReqID(String reqID);
    RequestReview getReqReviewByTaskId(String taskId);
    RequestCommit getReqCommitByTaskId(String taskId);
}
