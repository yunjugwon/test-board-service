package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.model.SampleBoard;


public interface SampleBoardService {
	/**
	 * 사용자 정보 가져오
	 * @return
	 * @throws Exception 
	 */
	public List<SampleBoard> selectBoards() throws Exception;	
	public void insertBoards(SampleBoard sampleBoards) throws Exception;
	public int updateBoards(SampleBoard sampleBoards) throws Exception;
	public int deleteBoards(String boardId) throws Exception;
	
}
