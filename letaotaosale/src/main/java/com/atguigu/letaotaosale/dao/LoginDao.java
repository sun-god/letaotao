package com.atguigu.letaotaosale.dao;

import com.atguigu.letaotaosale.bean.T_MALL_USER_ACCOUNT;

public interface LoginDao {

	T_MALL_USER_ACCOUNT select_user_by_mch_mm(T_MALL_USER_ACCOUNT user);

}
