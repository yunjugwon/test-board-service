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
import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.rabbitmq.BroadcastMessageProducer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Hello Service API")
@RequestMapping(value="/file")
@RestController
public class SampleFileController {
	@Autowired
	private SampleFileDao sampleFileDao;
	
	
	/**
	 * file upload
	 * @param userId
	 * @param userNm
	 * @param addr
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="정보 등록하기 ")
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResponseEntity <String > setUserInsert(
			@RequestParam("userId") String userId,
			@RequestParam("userNm") String userNm,
			@RequestParam("addr") String addr,
			@RequestParam("file") MultipartFile file
		) throws Exception { 
		SampleUser sampleUser = new SampleUser();
		List<SampleUser> list = null;
		
		sampleUser.setUserId(userId);
		sampleUser.setUserNm(userNm);
		sampleUser.setAddr(addr);
		sampleUser.setFile(file.getBytes());
		sampleUser.setFileName(file.getOriginalFilename());
		
		
		log.info("Start db insert");
		int re  = sampleFileDao.insertFile(sampleUser);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	
	/**
	 * file download
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/download/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(
			@PathVariable (name="userId", required = true) String userId
		) throws IOException {
		
		
		SampleUser re = null;
		try {
			log.info("Start db select");
			re = sampleFileDao.selectFileById(userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Content-disposition", "attachment; filename=\"" + re.getFileName() + "\"");
//		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(re.getFile());

		
		return ResponseEntity
				.ok()
				.headers(headers)
//				.contentLength(resource.getFile().length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
}
