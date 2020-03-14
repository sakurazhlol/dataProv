package com.ise.service.impl;

import cn.iselab.bcclient.model.ReportMix;
import com.ise.dao.DocDao;
import com.ise.service.FinalRepService;
import com.ise.service.IdService;
import com.ise.web.VO.FinalReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinalRepServiceImpl implements FinalRepService {

    @Autowired
    private DocDao docDao;

    @Autowired
    private IdService idService;

    @Override
    public ReportMix uploadFinalRep(ReportMix reportMix) {
        String docId = idService.genId();
        this.docDao.createDoc(docId,reportMix);
        return reportMix;
    }

    @Override
    public ReportMix getFinalRepByTaskID(String taskID) {
        List<ReportMix> result = new ArrayList<>();
        this.docDao.getDocByKV("taskId",taskID,ReportMix.class).forEach(v->{
            result.add((ReportMix) v);
        });
        if(result.isEmpty()){
            return null;
        }
       return result.get(0);
    }
}
