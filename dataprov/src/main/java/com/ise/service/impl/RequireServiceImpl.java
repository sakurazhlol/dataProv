package com.ise.service.impl;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;
import com.ise.dao.DocDao;
import com.ise.service.IdService;
import com.ise.service.RequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequireServiceImpl implements RequireService {

    @Autowired
    private IdService idService;

    @Autowired
    private DocDao docDao;

    @Override
    public RequestCommit uploadRequire(RequestCommit requestCommit) {
        String docId = idService.genId();
        this.docDao.createDoc(docId,requestCommit);
        return requestCommit;
    }

    @Override
    public RequestReview uploadReqReview(RequestReview requestReview) {
        String docId = idService.genId();
        this.docDao.createDoc(docId,requestReview);
        return requestReview;
    }

    @Override
    public List<RequestCommit> getReqCommitByReqUserId(String reqUsrID) {
        List<RequestCommit> result = new ArrayList<>();
        this.docDao.getDocByKV("requesterId",reqUsrID,RequestCommit.class).forEach(v->{result.add((RequestCommit) v);});
        return result;
    }

    @Override
    public RequestReview getReqReviewByReqID(String reqID) {
        List<RequestReview> result = new ArrayList<>();
        this.docDao.getDocByKV("requestId",reqID,RequestCommit.class).forEach(v->{result.add((RequestReview) v);});
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    @Override
    public RequestReview getReqReviewByTaskId(String taskId) {
        return (RequestReview) this.docDao.getDocByKV("taskId",taskId,RequestReview.class).get(0);
    }

    @Override
    public RequestCommit getReqCommitByTaskId(String taskId) {
        return (RequestCommit) this.docDao.getDocByKV("taskId",taskId,RequestCommit.class).get(0);
    }


//
//    @Override
//    public ReqReviewVO uploadReqReview(ReqReviewVO reqReviewVO) {
//        return null;
//    }
//
//    @Override
//    public DemandCommit getRequireByReqId(String reqId) {
//        return requireDao.getDemandByReqId(reqId);
//    }


}
