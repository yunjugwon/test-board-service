package com.springboot.microservices.mvp.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.rabbitmq.BroadcastMessageProducer;
import com.springboot.microservices.mvp.service.SampleUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Hello Service API")
@RequestMapping(value="/user")
@RestController
public class SampleUserController {
	
	private String msgTemplate = "%s님  반갑습니다.";
	private final AtomicLong  vistorConouter = new AtomicLong();
	
	@Autowired
	private SampleUserDao sampleUserDao;
	
	@Autowired
	private SampleUserService sampleUserService;
	
	@Autowired
	private BroadcastMessageProducer broadcastMessageProducer;
	
	
	@ApiOperation(value="사용자 정보 가져오기 ")
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity <List<SampleUser>> getUserList() { 
		
		List<SampleUser> list = null;
		try {
			log.info("Start db select");
//			list = sampleUserDao.selectUser();
			
			list = sampleUserService.selectUser();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("user counts :"+list.size());
		
		return new ResponseEntity<List<SampleUser>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기 2 - TypeAlias ")
	@RequestMapping(value="/users2", method=RequestMethod.GET)
	public ResponseEntity <List<SampleUser>> getUserList2() { 
		
		List<SampleUser> list = null;
		try {
			log.info("Start db select");
			list = sampleUserDao.selectUser2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("user counts :"+list.size());
		
		return new ResponseEntity<List<SampleUser>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="아이디로 사용자 정보 가져오기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public ResponseEntity <SampleUser> getUsserById(
				@PathVariable (name="userId", required = true) String userId
			) { 
		SampleUser re = null;
		try {
			log.info("Start db select");
			re = sampleUserDao.selectUserById(userId);
			
			// rabbitmq 
//			broadcastMessageProducer.produceChargeOrder(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SampleUser> (re, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 변경하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
	public ResponseEntity <String > setUserUpdate(
			@PathVariable(name="userId",required = true ) String userId, 
			@RequestBody SampleUser sampleUer
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db update");
		sampleUer.setUserId(userId);
		int re  = sampleUserDao.updateUser(sampleUer);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 등록하기 ")
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity <String > setUserInsert(
			@RequestBody SampleUser sampleUer
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db insert");
		int re  = sampleUserDao.insertUser(sampleUer);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 삭제하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity <String > setUserDelete(
			@PathVariable(name="userId",required = true ) String userId
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db insert");
		int re  = sampleUserDao.deleteUser(userId);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기  DB ")
	@RequestMapping(value="/db/test", method=RequestMethod.GET)
	public ResponseEntity <String> getUserListTest() { 
		
		int test = -1;
		try {
			log.info("Start db select");
			test = sampleUserDao.selectTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("user counts :"+test);
		
		return new ResponseEntity<String> (test+"", HttpStatus.OK);
	}	

	
	@ApiOperation(value="다른 서비스 정보 가져오기  ")
	@RequestMapping(value="/other/service", method=RequestMethod.GET)
	public ResponseEntity <Object> geOtherService() { 
		
		log.info("Start call other services api");
		
	    final String uri = "http://localhost:2222/accounts/list";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    Object result = restTemplate.getForObject(uri, Object.class);
	     
		
		return new ResponseEntity<Object> (result, HttpStatus.OK);
	}		
	

	
}
