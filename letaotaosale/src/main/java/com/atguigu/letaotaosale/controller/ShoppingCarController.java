package com.atguigu.letaotaosale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.letaotaosale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.letaotaosale.service.ShoppingCarService;
import com.atguigu.letaotaosale.utils.MyJsonUtil;
/*
 * 购物车的功能分析
 * 1.用户未登录-》cookie中没有数据-》新增数据
 * 2.用户未登录-》cookie中有数据-》新数据-》新增
 * 3.用户未登录-》cookie中有数据-》老数据-》更新
 * 4.用户已登录-》db没有数据-》新增
 * 5.用户已登录-》db中有数据-》老数据-》更新
 * 6.用户已登录-》db中有数据-》新数据-》新增
 * 7.用户登录-》cookie中没有数据-》db中查询同步到session中
 * 8.用户登录-》cookie中有数据 -》db中没有数据-》将cookie中的数据插入到db中
 * 9.用户登录-》cookie中有数据-》db中有数据并且是老数据-》合并
 * 10.用户登录-》cookie中有数据-》db中有数据并且是新数据-》新增
 */

@Controller
public class ShoppingCarController {

	@Autowired
	ShoppingCarService shoppingCarServiceImp;

	@ResponseBody
	@RequestMapping("add_car")
	public ModelAndView add_shoppingcar(T_MALL_SHOPPINGCAR car
			,HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		//用户未登录
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user==null) {
			List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
			//cookie中没有数据
			Cookie[] cookies = request.getCookies();
			if (cookies == null ||cookies.length==0) {
				
			}else {
				//cookie中有数据
				 list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
				String list_car_cookie_json = "";
				for (int i = 0; i < cookies.length; i++) {
				
					if (cookies[i].getName().equals("cookie_list_car")) {
						list_car_cookie_json=cookies[i].getValue();
					}
				}
				if (!list_car_cookie_json.equals("")) {
					//cookie中有购物车中的数据					list_car = MyJsonUtil.json_to_object(list_car_cookie_json);
					//判断是否是新值还是老值
					boolean b =if_new_car(list_car,car);
					if(b) {
						//cookie中有数据但是新增数据是新数据-新增
						list_car.add(car);
					}else {
						//cookie中有数据但是新增数据是老数据的-更新
						for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_car) {
							if (t_MALL_SHOPPINGCAR.getSku_id()==car.getSku_id()) {
								t_MALL_SHOPPINGCAR.setTjshl(t_MALL_SHOPPINGCAR.getTjshl()+car.getTjshl());
								t_MALL_SHOPPINGCAR.setHj(t_MALL_SHOPPINGCAR.getSku_jg()*t_MALL_SHOPPINGCAR.getTjshl());
							}
						}
					}
				}else {
					//cookie中没有购物车数据
					list_car.add(car);
				}
			}
			//把购物车中的数据在添加到cookie中
			String cookie_list_car = MyJsonUtil.object_to_json(list_car);
			Cookie cookie = new Cookie("cookie_list_car", cookie_list_car);
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
			
			//用户登录
		}else {
			//用户已登录
			List<T_MALL_SHOPPINGCAR> list = shoppingCarServiceImp.select_list_car_from_db(user.getId());
			session.setAttribute("list_car_session", list);
			List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
			list_car = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("list_car_session");
			
			
			car.setYh_id(user.getId());
			if (list_car==null||list_car.size()==0) {
				//db中没有购物车数据，直接添加
				//同步到session中
				shoppingCarServiceImp.add_list_car(car);
				list_car.add(car);
			}else {
				//数据库中有购物车数据，判断是新值还是老值，新值直接天天，老值直接更新
				//同步到session中
				boolean if_new_car = if_new_car(list_car, car);
				if (if_new_car) {
					shoppingCarServiceImp.add_list_car(car);
					list_car.add(car);
				}else{
					for (int i = 0; i < list_car.size(); i++) {
						if (list_car.get(i).getSku_id()==car.getSku_id()) {
							list_car.get(i).setTjshl(list_car.get(i).getTjshl()+car.getTjshl());
							list_car.get(i).setHj(list_car.get(i).getSku_jg()*list_car.get(i).getTjshl());
							shoppingCarServiceImp.update_list_car(list_car.get(i));
						}
					}
				}
			}
			
		
			
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/sale_shoppingCar_success.do");
		modelAndView.addObject(car);
		return modelAndView;
	}
	
	@RequestMapping("/sale_shoppingCar_success")
	public String sale_shoppingcar_success(T_MALL_SHOPPINGCAR car) {
		return "sale_shoppingCar_success";
	}

	private boolean if_new_car(List<T_MALL_SHOPPINGCAR> list_car,
			T_MALL_SHOPPINGCAR car) {
		boolean b = true;
		for (int i = 0; i < list_car.size(); i++) {
			if (car.getSku_id()==list_car.get(i).getSku_id()) {
				b=false;
			}
		}
		return b;
	}


}
