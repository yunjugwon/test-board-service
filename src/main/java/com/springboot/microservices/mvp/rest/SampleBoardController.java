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
import com.springboot.microservices.mvp.dao.SampleBoardDao;
import com.springboot.microservices.mvp.model.SampleBoard;
import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.rabbitmq.BroadcastMessageProducer;
import com.springboot.microservices.mvp.service.SampleBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Hello Service API")
@RequestMapping(value="/board")
@RestController
public class SampleBoardController {
	
	private String msgTemplate = "%s님  반갑습니다.";
	private final AtomicLong  vistorConouter = new AtomicLong();
	
	@Autowired
	private SampleBoardDao sampleBoardDao;
	
	@Autowired
	private SampleBoardService sampleBoardService;
	
	@Autowired
	private BroadcastMessageProducer broadcastMessageProducer;
	
	
	@ApiOperation(value="게시판 정보 가져오기 ")
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public ResponseEntity <List<SampleBoard>> getBoardList() { 
		List<SampleBoard> list = null;
		try {
			list = sampleBoardService.selectBoards();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<SampleBoard>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시판 정보 저장하기 ")
	@RequestMapping(value="/boards", method=RequestMethod.POST)
	public ResponseEntity <String > setBoardInsert(@RequestBody SampleBoard sampleBoards) throws Exception{ 
		sampleBoardService.insertBoards(sampleBoards);
//		try {
//			sampleBoardService.insertBoards(sampleBoards);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		return new ResponseEntity<String > (HttpStatus.OK);
	}
	
	@ApiOperation(value="게시판 정보 변경 ")
	@RequestMapping(value="/boards/{boardId}", method=RequestMethod.PUT)
	public ResponseEntity <String > setBoardUpdate(@PathVariable(name="boardId",required = true ) String boardId, 
			@RequestBody SampleBoard sampleBoards) throws Exception{ 
		sampleBoards.setBoardId(boardId);
		sampleBoardService.updateBoards(sampleBoards);
//		try {
//			sampleBoardService.insertBoards(sampleBoards);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		return new ResponseEntity<String > (HttpStatus.OK);
	}
	@ApiOperation(value="게시판 정보 삭제 ")
	@RequestMapping(value="/boards/{boardId}", method=RequestMethod.DELETE)
	public ResponseEntity <String > setBoardDelete(@PathVariable(name="boardId",required = true ) String boardId) throws Exception{ 
		sampleBoardService.deleteBoards(boardId);
//		try {
//			sampleBoardService.insertBoards(sampleBoards);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		return new ResponseEntity<String > (HttpStatus.OK);
	}
	
		
}
