package com.springboot.microservices.mvp.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.microservices.mvp.model.Hello;
import com.springboot.microservices.mvp.model.RabbitMsg;
import com.springboot.microservices.mvp.model.SampleUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BroadcastMessageConsumers {
	
	@RabbitListener(queues = "${prop.rabbit.queue.hello}" )
	public void receiveMessageFromDirectExchangeWithOrderQueue(RabbitMsg message) {
		log.info("CHARGE_ORDER_QUEUE Receive : "+message.toString());
		//todo data를 수신해서 로직 처리 
	}
	
}
