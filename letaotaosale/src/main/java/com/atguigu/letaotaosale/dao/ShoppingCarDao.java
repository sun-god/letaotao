package com.atguigu.letaotaosale.dao;

import java.util.List;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCarDao {
	int add_list_car(T_MALL_SHOPPINGCAR car);
	List<T_MALL_SHOPPINGCAR> selct_list_car_from_db(int id);
	void update_list_car(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);



}
