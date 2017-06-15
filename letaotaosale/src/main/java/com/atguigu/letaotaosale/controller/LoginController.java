package com.atguigu.letaotaosale.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.letaotaosale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.letaotaosale.service.ShoppingCarService;
import com.atguigu.letaotaosale.serviceImp.LoginServiceImpl;
import com.atguigu.letaotaosale.utils.MyJsonUtil;


@Controller
public class LoginController {

	@Autowired
	LoginServiceImpl loginService;
	
	@Autowired
	ShoppingCarService shoppingCarServiceImp;

	@RequestMapping("gotoIndex")
	public String goto_index() {
		return "sale_index";
	}
	
	@RequestMapping("goto_login")
	public String goto_login() {
		return "sale_login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "sale_login";
	}

	@RequestMapping("/login")
	public String login1228(@CookieValue(value="cookie_list_car",required=false) String cookie_list_car, 
			T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response, ModelMap map) {

		// 本系统，此处调用用户认证的webservice接口
		T_MALL_USER_ACCOUNT user_login = loginService.login1228(user);

		if (user_login == null) {
			map.put("err", "用户名或者密码错误");
			return "sale_login";
		} else {
			// 用户数据放入sessin
			session.setAttribute("user", user_login);

			// 将用户昵称放入cookie
			String object_to_json = MyJsonUtil.object_to_json(user_login.getYh_nch());
			Cookie cookie = new Cookie("atguiguUser", object_to_json);
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
			
			// 对购物车数据合并
			List<T_MALL_SHOPPINGCAR> list = shoppingCarServiceImp.select_list_car_from_db(user.getId());
			cookie_db_list_car(cookie_list_car,user,list,session, response);
			
			
			

			return "sale_index";
		}

	}
//
//	 * 7.用户登录-》cookie中没有数据-》db中查询同步到session中
//	 * 8.用户登录-》cookie中有数据 -》db中没有数据-》将cookie中的数据插入到db中
//	 * 9.用户登录-》cookie中有数据-》db中有数据并且是老数据-》合并
//	 * 10.用户登录-》cookie中有数据-》db中有数据并且是新数据-》新增
	private void cookie_db_list_car(String cookie_list_car,
			T_MALL_USER_ACCOUNT user, List<T_MALL_SHOPPINGCAR> list,
			HttpSession session, HttpServletResponse response) {
	    //cookie 中没有数据
		List<T_MALL_SHOPPINGCAR> json_to_object = MyJsonUtil.json_to_object(cookie_list_car);
		
		if (json_to_object==null||json_to_object.size()==0) {
			session.setAttribute("list_car_session", list);
		}else {
			//cookiez 中有数据
			//db中没有数据
			if (list==null||list.size()==0) {
				
				session.setAttribute("list_car_session", list);
			}
			for (int i = 0; i < json_to_object.size(); i++) {
				
			}
		}
		
	}




}
