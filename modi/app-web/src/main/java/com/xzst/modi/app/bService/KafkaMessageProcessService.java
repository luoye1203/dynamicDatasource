package com.xzst.modi.app.bService;

import com.alibaba.fastjson.JSON;
import com.xzst.modi.app.dModel.ConsumerMessageBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProcessService {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void preProcessMessage(String messageStr){

        ConsumerMessageBean consumerMessageBean= JSON.parseObject(messageStr,ConsumerMessageBean.class);

        String resultSetId=consumerMessageBean.getResultSetId();




    }


}
