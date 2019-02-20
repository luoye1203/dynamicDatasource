package com.xzst.modi.app.aController;


import com.xzst.modi.app.bService.TemplateInfoService;
import com.xzst.modi.app.dModel.BaseResponse;
import com.xzst.modi.app.gCommon.JWTUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/model")
public class TemplateInfoController {
    private static final Logger LOG=Logger.getLogger(TemplateInfoController.class);

    @Autowired
    private TemplateInfoService templateInfoService;

    /**
     * 获取所有的模型名
     *
     * @return
     */
    @RequestMapping(value = "getModelAndResultByKeyword", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的模型名(含结果集)", notes = "获取所有的模型名(含结果集) ")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "keyword", paramType = "query", value = "查询关键字", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "无数据"), @ApiResponse(code = 202, message = "查询出现异常")})
    public BaseResponse getModelAndResultByKeyword(String keyword) {

        int code = 200;
        String message = "查询成功";
        List reList = null;

        try {

            reList = templateInfoService.getModelAndResultByKeyword(keyword);

            if (reList == null || reList.size() < 1) {
                code = 201;
                message = "无数据";
                return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
            }
            return BaseResponse.buildResponse().setObj(reList).setCode(code).setMessage(message).build();
        } catch (Exception e) {
            LOG.error(e.getStackTrace(), e);
            code = 202;
            message = "查询出现异常";
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        }

    }


}
