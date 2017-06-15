package com.atguigu.letaotaosale.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.letaotaosale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.letaotaosale.dao.LoginDao;
import com.atguigu.letaotaosale.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	public T_MALL_USER_ACCOUNT login1228(T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT user_login = loginDao.select_user_by_mch_mm(user);
		System.out.println(user_login);
		return user_login;
	}

}
