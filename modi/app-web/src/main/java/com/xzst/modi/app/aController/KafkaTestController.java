package com.xzst.modi.app.aController;

import com.xzst.modi.app.dModel.BaseResponse;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("kafka服务")
@RequestMapping("/kafka/")
public class KafkaTestController {
    private final Logger logger = Logger.getLogger(this.getClass());


    @Autowired
    private KafkaTemplate kafkaTemplate;


    @Value("${spring.kafka.producer.topic}")
    private String topicName;


    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation(value = "kafka发送消息",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse sendKafka(@RequestParam String message) {
        try {
            message="{resultSetId='139951', dataMap={rylx_3c90728c23dd49cca38ea15b46b5701e=在逃人员, ztrybh_d53c63bd77e5419fb486f7c0a1012a24=T3301100000002018125083, pjhjdz_c28b2d21e5234bba83263b023ee1b6c8=安徽省芜湖市南陵县三里镇三里街道九华山路100号木业市场1号楼（南陵驾校）, gxsjd_afedb9c6830943a698d9f38ad27afb28=20180114010121000, lyb_b9965c55a8cd45cfb094001181d369c6=驾驶人基本信息, sfzh_c779c5019c9a47a5b526837629ed7a74=340223199509205813, xm_d9c3b3cc649f4de9b2a3f1766b05c126=张远方}}";

            kafkaTemplate.send(topicName, "key", message);


//			logger.info("发送kafka成功.");
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
        return response;
    }

}
