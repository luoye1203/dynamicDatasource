package com.xzst.modi.app.aController;


import com.xzst.modi.app.dModel.BaseResponse;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试swagger集成服务")
@RequestMapping("/swagger/")
public class TestSwagger {

    private final Logger logger = Logger.getLogger(this.getClass());


    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation(value = "测试是否成功",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse sendKafka(@RequestParam String message) {
        try {
            logger.info("测试成功");
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
        return response;
    }

}
