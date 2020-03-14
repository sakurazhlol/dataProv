package com.ise.web.controller;

import cn.iselab.bcclient.model.ReportReview;
import cn.iselab.bcclient.model.TestReport;
import com.ise.Logic.ReportLogic;
import com.ise.web.VO.TestRepCollection;
import com.ise.web.VO.TestReportVO;
import com.ise.web.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Report Doc")
@RestController
public class ReportController {
    @Autowired
    private ReportLogic reportLogic;

    @ApiOperation(value = "/uploadTestReport", notes = "上传测试报告")
    @RequestMapping(value = "/testReport", method = RequestMethod.POST)
    public ResponseResult<TestReport> uploadTestReport(@RequestBody TestReport testReport){
        TestReport result = reportLogic.uploadTestReport(testReport);
        return new ResponseResult<TestReport>().setData(result);
    }

    @ApiOperation(value = "/uploadRepReview", notes = "上传测试报告审核数据")
    @RequestMapping(value = "/reportReview", method = RequestMethod.POST)
    public ResponseResult<ReportReview> uploadRepReview(@RequestBody ReportReview reportReview){
        ReportReview result = reportLogic.uploadRepReview(reportReview);
        return new ResponseResult<ReportReview>().setData(result);
    }


    @ApiOperation(value = "/getTestReportByUsrId", notes = "根据众测工人ID获得测试报告数据")
    @RequestMapping(value = "/testReport/{usrId}", method = RequestMethod.GET)
    public ResponseResult<TestRepCollection> getRepByUserId(@PathVariable String usrId){
        TestRepCollection result = reportLogic.getTestReportByUsrID(usrId);
        return new ResponseResult<TestRepCollection>().setData(result);
    }


}
