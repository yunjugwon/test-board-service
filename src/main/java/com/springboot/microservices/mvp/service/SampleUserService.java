package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.model.SampleUser;


public interface SampleUserService {
	/**
	 * 사용자 정보 가져오
	 * @return
	 * @throws Exception 
	 */
	public List<SampleUser> selectUser() throws Exception;	
}
