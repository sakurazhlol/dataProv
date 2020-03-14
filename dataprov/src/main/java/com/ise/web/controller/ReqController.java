package com.ise.web.controller;

import cn.iselab.bcclient.model.RequestCommit;
import cn.iselab.bcclient.model.RequestReview;
import com.ise.Logic.RequireLogic;
import com.ise.web.VO.ReqDataCollection;
import com.ise.web.VO.ReqReviewVO;
import com.ise.web.VO.RequestVO;
import com.ise.web.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Require Doc")
@RestController
public class ReqController {
    @Autowired
    private RequireLogic requireLogic;

    @ApiOperation(value = "/uploadRequire", notes = "上传需求数据")
    @RequestMapping(value = "/requireData", method = RequestMethod.POST)
    public ResponseResult<RequestCommit> uploadRequire(@RequestBody RequestCommit requestCommit){
        RequestCommit result = requireLogic.uploadRequire(requestCommit);
        return new ResponseResult<RequestCommit>().setData(result);
    }

    @ApiOperation(value = "/uploadReqReview", notes = "上传需求审核数据")
    @RequestMapping(value = "/requireReview", method = RequestMethod.POST)
    public ResponseResult<RequestReview> uploadReqReview(@RequestBody RequestReview requestReview){
        RequestReview result = requireLogic.uploadReqReview(requestReview);
        return new ResponseResult<RequestReview>().setData(result);
    }

    @ApiOperation(value = "/getRequire", notes = "根据需求方ID获得溯源数据")
    @RequestMapping(value = "/require/{reqUserId}", method = RequestMethod.GET)
    public ResponseResult<ReqDataCollection> getRequire(@PathVariable String reqUserId){
        ReqDataCollection result = requireLogic.getReqDataByUserId(reqUserId);
        return new ResponseResult<ReqDataCollection>().setData(result);
    }



}
