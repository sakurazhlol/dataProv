package com.ise.Logic;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;
import com.ise.web.VO.ReqDataCollection;

public interface RequireLogic {

    RequestCommit uploadRequire(RequestCommit requestCommit);

    RequestReview uploadReqReview(RequestReview requestReview);

    ReqDataCollection getReqDataByUserId(String reqId);
}
