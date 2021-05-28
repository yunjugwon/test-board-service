package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.dao.SampleBoardDao;
import com.springboot.microservices.mvp.model.SampleBoard;


@Service
public class SampleBoardServiceImpl implements SampleBoardService{

	@Autowired
	private SampleBoardDao sampleBoardDao;
	
	@Override
	public List<SampleBoard> selectBoards() throws Exception {
		// TODO Auto-generated method stub
		return sampleBoardDao.selectBoards();
	}
	public void insertBoards(SampleBoard sampleBoards) throws Exception{
			sampleBoardDao.insertBoards(sampleBoards);
			//return 0;
	}
	public int updateBoards(SampleBoard sampleBoards) throws Exception{
		return sampleBoardDao.updateBoards(sampleBoards);
	}
	public int deleteBoards(String boardId) throws Exception{
		return sampleBoardDao.deleteBoards(boardId);
	}	
	
	public SampleBoard selectBoardById(String boardId) throws Exception{
		return sampleBoardDao.selectBoardById(boardId);
	}

}
