package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.dao.SampleUserDao;
import com.springboot.microservices.mvp.model.SampleUser;


@Service
public class SampleUserServiceImpl implements SampleUserService{

	@Autowired
	private SampleUserDao sampleUserDao;
	
	@Override
	public List<SampleUser> selectUser() throws Exception {
		// TODO Auto-generated method stub
		return sampleUserDao.selectUser();
	}

}
