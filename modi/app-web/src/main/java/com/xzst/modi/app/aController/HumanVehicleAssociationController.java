package com.xzst.modi.app.aController;

import com.xzst.modi.app.bService.HumanVehicleAssociationService;
import com.xzst.modi.app.dModel.BaseResponse;
import com.xzst.modi.app.dModel.p2cgl.FugitiveModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import com.xzst.modi.app.gCommon.JWTUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @Autowired
    private JWTUtil jwtUtil;


    /**
     *
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


    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "addConfig", method = RequestMethod.POST)
    @ApiOperation(value = "添加配置", notes = "添加配置")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "hvaConfigModel", paramType = "body", value = "参数", dataType = "HVAConfigModel")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "数据已存在"), @ApiResponse(code = 202, message = "操作异常")})
    public BaseResponse addConfig(@RequestBody HVAConfigModel hvaConfigModel) {

        int code = 200;
        String message = "添加成功";
        try {

            HVAConfigModel model=humanVehicleAssociationService.getConfig();
            if(model!=null){
                message = "数据已存在";
                code = 202;
                return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
            }

            humanVehicleAssociationService.addConfig(hvaConfigModel);
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        } catch (Exception e) {
            LOG.error(e.getStackTrace(), e);
            code = 202;
            message = "操作异常";
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        }

    }

    @RequestMapping(value = "delConfigByid", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除配置", notes = "删除配置 ")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "configId", paramType = "query", value = "配置ID", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 202, message = "操作异常")})
    public BaseResponse getConfigById(@RequestParam String configId) {

        int code = 200;
        String message = "操作成功";
        HVAConfigModel reData = null;

        try {

            humanVehicleAssociationService.delConfig(configId);

            return BaseResponse.buildResponse().setObj(reData).setCode(code).setMessage(message).build();
        } catch (Exception e) {
            LOG.error(e.getStackTrace(), e);
            code = 202;
            message = "操作异常";
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        }

    }



    @RequestMapping(value = "getPageFocusCols", method = RequestMethod.GET)
    @ApiOperation(value = "获取人车关联需要关注的页面字段", notes = "获取人车关联需要关注的页面字段")
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "keyword", paramType = "query", value = "查询关键字", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "无数据"), @ApiResponse(code = 202, message = "查询出现异常")})
    public BaseResponse getPageFocusCols( ) {

        int code = 200;
        String message = "查询成功";
        List<Map<String,String>> reData = null;

        try {

            reData =humanVehicleAssociationService.getPageFocusCols();

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



    @RequestMapping(value = "getHvaTop20", method = RequestMethod.GET)
    @ApiOperation(value = "获取某日期下前20名", notes = "获取某日期下前20名")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "date", paramType = "query", value = "查询日期(2019-02-27)", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "无数据"), @ApiResponse(code = 202, message = "查询出现异常")})
    public BaseResponse getHvaTop20(@RequestParam String date) {

        int code = 200;
        String message = "查询成功";
        List<Map<String,Object>> reData = null;

        try {

            reData =humanVehicleAssociationService.getHvaTop20(date);

            if (reData == null || reData.size()<1 ) {
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





    @RequestMapping(value = "getFugitiveModels", method = RequestMethod.GET)
    @ApiOperation(value = "获取某日人车关联所有数据", notes = "获取某日人车关联所有数据")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "date", paramType = "query", value = "查询日期(2019-02-27)", dataType = "string")
            }
    )
    @ApiResponses(value = {@ApiResponse(code = 201, message = "无数据"), @ApiResponse(code = 202, message = "查询出现异常")})
    public BaseResponse getFugitiveModels(@RequestParam String date) {

        int code = 200;
        String message = "查询成功";
        List<FugitiveModel> reData = null;

        try {

            reData =humanVehicleAssociationService.getFugitiveModels(date);

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

    @RequestMapping(value = "archivedFugitive", method = RequestMethod.GET)
    @ApiOperation(value = "归档在逃人员", notes = "归档在逃人员")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", paramType = "query", value = "id", dataType = "string"),
                    @ApiImplicitParam(name = "archivedReason", paramType = "query", value = "归档原因", dataType = "string"),
            }
    )
    @ApiResponses(value = { @ApiResponse(code = 202, message = "操作异常")})
    public BaseResponse archivedFugitive(@RequestParam String id,@RequestParam String archivedReason) {

        int code = 200;
        String message = "操作成功";

        try {
            String yhbh=jwtUtil.getYhbhFromToken();
            humanVehicleAssociationService.archivedFugitive(id,archivedReason,yhbh);


            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        } catch (Exception e) {
            LOG.error(e.getStackTrace(), e);
            code = 202;
            message = "操作异常";
            return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
        }

    }






}
