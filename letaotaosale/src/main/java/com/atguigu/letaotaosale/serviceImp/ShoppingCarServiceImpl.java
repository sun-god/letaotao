package com.atguigu.letaotaosale.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.letaotaosale.dao.ShoppingCarDao;
import com.atguigu.letaotaosale.service.ShoppingCarService;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
	
	@Autowired
	ShoppingCarDao shoppingCarDao;



	@Override
	public int add_list_car(T_MALL_SHOPPINGCAR car) {
		int i = shoppingCarDao.add_list_car(car);
		return i;
	}



	@Override
	public void update_list_car(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR) {
	
		shoppingCarDao.update_list_car(t_MALL_SHOPPINGCAR);
	}



	@Override
	public List<T_MALL_SHOPPINGCAR> select_list_car_from_db(int id) {
		List<T_MALL_SHOPPINGCAR> list = shoppingCarDao.selct_list_car_from_db(id);
		return list;
	}

}
