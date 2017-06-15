package com.atguigu.letaotaosale.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.letaotaosale.dao.TestDao;
import com.atguigu.letaotaosale.service.IndexService;



@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	TestDao testDao;

	@Override
	public int testDb() {
		int select_test = testDao.select_test();
		return select_test;
	}

}
