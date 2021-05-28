package com.springboot.microservices.mvp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.microservices.mvp.model.SampleUser;
import com.springboot.microservices.mvp.model.SampleUserTest;


@Mapper
public interface SampleUserDaoTest {

	/**
	 * 사용자 전체 정보 가져오기 
	 * @return
	 * @throws Exception
	 */
	List<SampleUserTest> selectUser() throws Exception;	
	/**
	 * 사용자 등록하기 
	 * @param sampleUser
	 * @return
	 * @throws Exception
	 */
	int insertUser(SampleUserTest sampleUserTest) throws Exception;
	/**
	 * 사용자 정보 변경하
	 * @param sampleUser
	 * @return
	 * @throws Exception
	 */
	int updateUser(SampleUserTest sampleUserTest) throws Exception;
	/**
	 * 사용자 정보 삭제하기 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int deleteUser(String userId) throws Exception;	
	
	SampleUserTest selectUser1(String userId) throws Exception;

}
			