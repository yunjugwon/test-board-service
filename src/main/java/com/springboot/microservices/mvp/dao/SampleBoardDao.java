package com.springboot.microservices.mvp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.microservices.mvp.model.SampleBoard;


@Mapper
public interface SampleBoardDao {

	/**
	 * 사용자 전체 정보 가져오기 
	 * @return
	 * @throws Exception
	 */
	List<SampleBoard> selectBoards() throws Exception;
	
	void insertBoards(SampleBoard sampleBoard) throws Exception;
	
	int updateBoards(SampleBoard sampleBoard) throws Exception;
	
	int deleteBoards(String userId) throws Exception;
	
	SampleBoard selectBoardById(String boardId) throws Exception;
	
}
			