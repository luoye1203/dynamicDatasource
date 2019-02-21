package com.xzst.modi.app.bService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProcessService {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void preProcessMessage(String messageStr){

        logger.info(messageStr);

    }


}
