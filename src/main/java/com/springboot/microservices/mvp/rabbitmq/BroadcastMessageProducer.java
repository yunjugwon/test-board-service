package com.springboot.microservices.mvp.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springboot.microservices.mvp.model.Hello;
import com.springboot.microservices.mvp.model.RabbitMsg;
import com.springboot.microservices.mvp.model.SampleUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BroadcastMessageProducer {

	  @Value("${prop.rabbit.direct.exchange}")
	  private String EXCHANGE;
	  
	  @Value("${prop.rabbit.route.hello}")
	  private String ROUTING_KEY;
	  
	  @Autowired
	  private RabbitTemplate rabbitTemplate;

	  public void produceChargeOrder(RabbitMsg user){
		log.debug("EXCHANGE : "+EXCHANGE);
		log.debug("ROUTING_KEY : "+ROUTING_KEY);
	    log.debug("Send Message to Rabbitmq :" + user.toString());
	     rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, user);
	    
	  }

}
