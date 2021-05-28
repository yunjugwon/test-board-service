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
import com.springboot.microservices.mvp.dao.SampleUserDaoTest;
import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.model.SampleUserTest;
import com.springboot.microservices.mvp.rabbitmq.BroadcastMessageProducer;
import com.springboot.microservices.mvp.service.SampleUserServiceTest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Hello Service API")
@RequestMapping(value="/userTest")
@RestController
public class SampleUserControllerTest {
	
	private String msgTemplate = "%s님  반갑습니다.";
	private final AtomicLong  vistorConouter = new AtomicLong();
	@Autowired
	private SampleUserDaoTest sampleUserDaoTest;
	@Autowired
	private SampleUserServiceTest sampleUserServiceTest;
	
	@ApiOperation(value="사용자 정보 가져오기 ")
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity <List<SampleUserTest>> getUserList() { 
		
		List<SampleUserTest> list = null;
		try {
			log.info("Start db select888");
//			list = sampleUserDao.selectUser();
			
			list = sampleUserServiceTest.selectUser();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("user counts :"+list.size());
		
		return new ResponseEntity<List<SampleUserTest>> (list, HttpStatus.OK);
	}
		
	@ApiOperation(value="사용자 정보 등록하기 ")
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity <String > setUserInsert(
			@RequestBody SampleUserTest sampleUerTest
		) throws Exception { 	
		log.info("Start db insert");
		int re  = sampleUserDaoTest.insertUser(sampleUerTest);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	
	@ApiOperation(value="사용자 정보 변경하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
	public ResponseEntity <String > setUserUpdate(
			@PathVariable(name="userId",required = true ) String userId, 
			@RequestBody SampleUserTest sampleUerTest
		) throws Exception { 
		
		log.info("Start db update");
		sampleUerTest.setUserId(userId);
		int re  = sampleUserDaoTest.updateUser(sampleUerTest);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 삭제하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity <String > setUserDelete(
			@PathVariable(name="userId",required = true ) String userId
		) throws Exception { 
		
		log.info("Start db delete");
		int re  = sampleUserDaoTest.deleteUser(userId);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기 22")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public ResponseEntity <SampleUserTest> getUserList1(
			@PathVariable(name="userId",required = true ) String userId
			) { 
		
		SampleUserTest re = null;
		try {
			log.info("Start db select id");
			re = sampleUserDaoTest.selectUser1(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SampleUserTest> (re, HttpStatus.OK);
	}
	
}
