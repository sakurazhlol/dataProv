package com.ise.web.controller;

import cn.iselab.bcclient.model.ReportMix;
import com.ise.Logic.FinalReportLogic;
import com.ise.web.VO.FinalRepVO;
import com.ise.web.VO.FinalReportVO;
import com.ise.web.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "FinalReport Doc")
@RestController
public class FinalRepController {
    @Autowired
    private FinalReportLogic finalReportLogic;

    @ApiOperation(value = "/uploadFinalReport", notes = "上传最终报告")
    @RequestMapping(value = "/finalReport", method = RequestMethod.POST)
    public ResponseResult<FinalReportVO> uploadFinalReport(@RequestBody FinalReportVO finalReportVO){
        FinalReportVO result = finalReportLogic.uploadFinalReport(finalReportVO);
        return new ResponseResult<FinalReportVO>().setData(result);
    }

    @ApiOperation(value = "/getFinalReport", notes = "获取最终报告")
    @RequestMapping(value = "/finalReport/{taskId}", method = RequestMethod.GET)
    public ResponseResult<FinalRepVO> getRequire(@PathVariable String taskId){
        FinalRepVO result = finalReportLogic.getFinalReport(taskId);
        return new ResponseResult<FinalRepVO>().setData(result);
    }

}
