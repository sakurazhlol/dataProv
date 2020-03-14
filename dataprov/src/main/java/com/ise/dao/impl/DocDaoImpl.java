package com.ise.dao.impl;

import cn.iselab.bcclient.constant.DocTypeConst;
import cn.iselab.bcclient.model.*;
import com.ise.dao.DocDao;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import cn.iselab.bcclient.service.DocService;
import cn.iselab.bcclient.data.KVPair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import java.lang.reflect.Type;

@Service
public class DocDaoImpl implements DocDao {

    @Autowired
    private DocService docService;

    @Override
    public Doc createDoc(String docId, Doc doc) {
        if(docId == null) {
            return null;
        }
        try {
            docService.save(docId, doc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return doc;
    }


    @Override
    public Doc getLatestDocById(String docId, Type t) {
        Doc result = null;
        try {
            result = docService.findOne(t, docId);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Doc> getDocByKV(String key, String value, Type type) {
        List<Doc> result = new ArrayList<>();
        Integer typeInt = -1;
        if(type.equals(RequestCommit.class)) {
            typeInt = DocTypeConst.RequestCommit;
        }else if(type.equals(RequestReview.class)) {
            typeInt = DocTypeConst.RequestReview;
        }else if(type.equals(TestReport.class)) {
            typeInt = DocTypeConst.TestReport;
        }else if(type.equals(ReportReview.class)) {
            typeInt = DocTypeConst.ReportReview;
        }else if(type.equals(ReportMix.class)){
            typeInt = DocTypeConst.ReportMix;
        }else if(type.equals(TaskState.class)){
            typeInt = DocTypeConst.TaskState;
        }

        try {
            List<KVPair<Doc>>  resultList = null;
            if(typeInt.equals(DocTypeConst.RequestCommit)) {
                resultList = docService.query(RequestCommit.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }else if(typeInt.equals(DocTypeConst.RequestReview)) {
                resultList = docService.query(RequestReview.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }else if(typeInt.equals(DocTypeConst.TestReport)) {
                resultList = docService.query(TestReport.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }else if(typeInt.equals(DocTypeConst.ReportReview)) {
                resultList = docService.query(ReportReview.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }else if(typeInt.equals(DocTypeConst.ReportMix)) {
                resultList = docService.query(ReportMix.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }else if(typeInt.equals(DocTypeConst.TaskState)) {
                resultList = docService.query(TaskState.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"," +"\""+ key +"\""+":"+"\""+ value +"\"}}");
            }
            resultList.forEach(
                    p->{
                        result.add(p.getDoc());
                    }
            );
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Doc> getAllDoc(Type type) {
        List<Doc> result = new ArrayList<>();
        Integer typeInt = -1;
        if(type.equals(RequestCommit.class)) {
            typeInt = DocTypeConst.RequestCommit;
        }else if(type.equals(RequestReview.class)) {
            typeInt = DocTypeConst.RequestReview;
        }else if(type.equals(TestReport.class)) {
            typeInt = DocTypeConst.TestReport;
        }else if(type.equals(ReportReview.class)) {
            typeInt = DocTypeConst.ReportReview;
        }else if(type.equals(ReportMix.class)){
            typeInt = DocTypeConst.ReportMix;
        }else if(type.equals(TaskState.class)){
            typeInt = DocTypeConst.TaskState;
        }

        try {
            List<KVPair<Doc>>  resultList = null;
            if(typeInt.equals(DocTypeConst.RequestCommit)) {
                resultList = docService.query(RequestCommit.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }else if(typeInt.equals(DocTypeConst.RequestReview)) {
                resultList = docService.query(RequestReview.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }else if(typeInt.equals(DocTypeConst.TestReport)) {
                resultList = docService.query(TestReport.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }else if(typeInt.equals(DocTypeConst.ReportReview)) {
                resultList = docService.query(ReportReview.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }else if(typeInt.equals(DocTypeConst.ReportMix)) {
                resultList = docService.query(ReportMix.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }else if(typeInt.equals(DocTypeConst.TaskState)) {
                resultList = docService.query(TaskState.class,"{\"selector\":{"+ "\"type\":\""+ typeInt+ "\"" +"}}");
            }
            resultList.forEach(
                    p->{
                        result.add(p.getDoc());
                    }
            );
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ProposalException e) {
            e.printStackTrace();
        }
        return result;
    }
}
