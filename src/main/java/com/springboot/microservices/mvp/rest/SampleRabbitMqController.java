package com.springboot.microservices.mvp.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.microservices.mvp.dao.SampleFileDao;
import com.springboot.microservices.mvp.dao.SampleUserDao;
import com.springboot.microservices.mvp.model.Hello;
import com.springboot.microservices.mvp.model.RabbitMsg;
import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.rabbitmq.BroadcastMessageProducer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Rabbitmq Service API")
@RequestMapping(value="/rabbit")
@RestController
public class SampleRabbitMqController {
	
	@Autowired
	BroadcastMessageProducer broadcastMessageProducer;
	
	@ApiOperation(value="사용자 정보 등 ")
	@RequestMapping(value="/send", method=RequestMethod.POST)
	public ResponseEntity <String > setUserInsert(
			@RequestBody RabbitMsg sampleUer
		) throws Exception { 
		
		log.info("Start insert :"+sampleUer.toString());
		
		broadcastMessageProducer.produceChargeOrder(sampleUer);
		log.debug("result :" );
		
		return new ResponseEntity<String> ("", HttpStatus.OK);
	}
	

}
