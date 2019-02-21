package com.xzst.modi.app.kafka;

/**
 * Created by LHT on 2018/7/5.
 */


import com.xzst.modi.app.bService.KafkaMessageProcessService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private KafkaMessageProcessService kafkaMessageProcessService;

	@KafkaListener(topics = {"modi"})
	public void kafkaConsumerListenterMethod(ConsumerRecord<?, ?> record) {

		if(record!=null&&record.value()!=null&&!record.value().toString().trim().equals("")){
			String message=  record.value().toString();
			try {
//				logger.info("消费的topic为:  "+record.topic());
//				logger.info("消费的topic为:  "+record);
//				ConsumerMessageBean messageBean= JSONTools.string2JavaBean(message,ConsumerMessageBean.class);
				logger.info(message.toString());
				kafkaMessageProcessService.preProcessMessage(message);
			} catch (Exception e) {
				logger.error("data为:"+ message+ "  数据格式无效,请查证...");
			}

		}else{
			logger.error("接收到空消息,摒弃...");
		}

	}





}