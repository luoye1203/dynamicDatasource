package com.xzst.modi.app.aController;


import com.xzst.modi.app.dModel.BaseResponse;
import com.xzst.modi.app.hConfig.FrontendConfigPorperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("案件月报分析")
@RequestMapping("/ajybfx/")
public class AjybfxController {

    private final Logger logger = Logger.getLogger(this.getClass());


    @Autowired
    private FrontendConfigPorperties frontendConfigPorperties;


    @RequestMapping(value = "/getConfigProperties", method = RequestMethod.GET)
    @ApiOperation(value = "获取前端配置参数",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse sendKafka() {
        try {
            logger.info(frontendConfigPorperties);
        } catch (Exception e) {
            logger.error("获取失败", e);
            return BaseResponse.buildResponse().setCode(200).setMessage("获取失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj(frontendConfigPorperties).setCode(200).setMessage("获取成功").build();
        return response;
    }

}
