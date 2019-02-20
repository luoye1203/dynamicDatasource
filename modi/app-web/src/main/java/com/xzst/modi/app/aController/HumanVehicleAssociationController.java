package com.xzst.modi.app.aController;

import com.xzst.modi.app.bService.HumanVehicleAssociationService;
import com.xzst.modi.app.dModel.BaseResponse;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * lht
 * 2019-2-15 16:22:54
 */
@RestController
@RequestMapping("p2cgl")
public class HumanVehicleAssociationController {

    private static final Logger LOG=Logger.getLogger( HumanVehicleAssociationController.class);

    @Autowired
    private HumanVehicleAssociationService humanVehicleAssociationService;


    /**
     * 获取所有的模型名
     *
     * @return
     */
    @RequestMapping(value = "getConfig", method = RequestMethod.GET)
    @ApiOperation(value = "获取配置记录(只有一条)", notes = "获取配置记录(只有一条) ")
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "keyword", paramType = "query", value = "查询关键字", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "无数据"), @ApiResponse(code = 202, message = "查询出现异常")})
    public BaseResponse getConfig( ) {

        int code = 200;
        String message = "查询成功";
        HVAConfigModel reData = null;

        try {

            reData =humanVehicleAssociationService.getConfig();

            if (reData == null ) {
                code = 201;
                message = "无数据";
                return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
            }
            return BaseResponse.buildResponse().setObj(reData).setCode(code).setMessage(message).build();
        } catch (Exception e) {
            LOG.error(e.getStackTrace(), e);
            code = 202;
            message = "查询出现异常";
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        }

    }





}
