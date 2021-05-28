package com.springboot.microservices.mvp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.microservices.mvp.model.SampleUser;


@Mapper
public interface SampleFileDao {


	
	/**
	 * 등록하기 
	 * @param sampleUser
	 * @return
	 * @throws Exception
	 */
	int insertFile(SampleUser sampleUser) throws Exception;
	
	SampleUser selectFileById(String userId) throws Exception;	

}
			