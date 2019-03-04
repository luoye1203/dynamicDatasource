package com.xzst.modi.app.bService;

import com.alibaba.fastjson.JSON;
import com.xzst.modi.app.cDao.KafkaDao;
import com.xzst.modi.app.dModel.ConsumerMessageBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KafkaMessageProcessService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private RcglService rcglService;
    @Autowired
    private KafkaDao kafkaDao;

    public void preProcessMessage(String messageStr){

        ConsumerMessageBean consumerMessageBean= JSON.parseObject(messageStr,ConsumerMessageBean.class);

        String resultSetId=consumerMessageBean.getResultSetId();

        List<Map<String,String>> resultsetIds=kafkaDao.getAllConfigedResultSetIds();
        for (Map<String,String> map : resultsetIds) {

            if("人车关联".equals(map.get("TYPE"))&&resultSetId.equals(map.get("RESULTSETID"))){
                rcglService.insertMessageFromKafka(consumerMessageBean);
            }


        }







    }


}
