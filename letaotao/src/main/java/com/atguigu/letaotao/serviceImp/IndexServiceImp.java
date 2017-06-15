package com.atguigu.letaotao.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.letaotao.dao.TestDao;
import com.atguigu.letaotao.service.IndexService;



@Service
public class IndexServiceImp implements IndexService {

	@Autowired
	TestDao testDao;

	@Override
	public int testDb() {
		int select_test = testDao.select_test();
		return select_test;
	}

}
