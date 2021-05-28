package com.springboot.microservices.mvp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.microservices.mvp.model.SampleUser;


@Mapper
public interface SampleUserDao {

	/**
	 * 사용자 전체 정보 가져오기 
	 * @return
	 * @throws Exception
	 */
	List<SampleUser> selectUser() throws Exception;	
	
	List<SampleUser> selectUser2() throws Exception;		
	
	/**
	 * 아이디로 사용자 정보 조회하
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	SampleUser selectUserById(String userId) throws Exception;	
	
	/**
	 * 사용자 정보 변경하
	 * @param sampleUser
	 * @return
	 * @throws Exception
	 */
	int updateUser(SampleUser sampleUser) throws Exception;
	
	/**
	 * 사용자 등록하기 
	 * @param sampleUser
	 * @return
	 * @throws Exception
	 */
	int insertUser(SampleUser sampleUser) throws Exception;
	
	/**
	 * 사용자 정보 삭제하기 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int deleteUser(String userId) throws Exception;		
	
	int selectTest() throws Exception;

}
			