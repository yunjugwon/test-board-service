package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.model.SampleUserTest;


public interface SampleUserServiceTest {
	/**
	 * 사용자 정보 가져오
	 * @return
	 * @throws Exception 
	 */
	public List<SampleUserTest> selectUser() throws Exception;	
}
