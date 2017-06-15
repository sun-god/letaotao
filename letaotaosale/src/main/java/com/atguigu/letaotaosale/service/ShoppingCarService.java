package com.atguigu.letaotaosale.service;

import java.util.List;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCarService {


	/**
	 * 添加购物车数据
	 * @param car
	 * @return
	 */
	int add_list_car(T_MALL_SHOPPINGCAR car);

	/**
	 * 跟新购物车数据
	 * @param t_MALL_SHOPPINGCAR
	 */
	void update_list_car(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);

	/**
	 * 查询购物车数据
	 * @param id
	 * @return
	 */
	List<T_MALL_SHOPPINGCAR> select_list_car_from_db(int id);

}
