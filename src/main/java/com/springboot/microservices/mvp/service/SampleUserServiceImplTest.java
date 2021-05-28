package com.springboot.microservices.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.mvp.dao.SampleUserDaoTest;
import com.springboot.microservices.mvp.model.SampleUserTest;


@Service
public class SampleUserServiceImplTest implements SampleUserServiceTest{

	@Autowired
	private SampleUserDaoTest sampleUserDaoTest;
	
	@Override
	public List<SampleUserTest> selectUser() throws Exception {
		// TODO Auto-generated method stub
		return sampleUserDaoTest.selectUser();
	}

}
