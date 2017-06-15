package com.atguigu.letaotaosale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.letaotaosale.serviceImp.IndexServiceImpl;



@Controller
public class IndexController {

	@Autowired
	IndexServiceImpl indexservice;

	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap map) {
		// String yh_nch = "";
		// Cookie[] cookies = request.getCookies();
		// if (cookies != null) {
		// for (int i = 0; i < cookies.length; i++) {
		// if (cookies[i].getName().equals("atguiguUser")) {
		// try {
		// yh_nch = URLDecoder.decode(cookies[i].getValue(), "utf-8");
		// System.out.println(yh_nch);
		// } catch (UnsupportedEncodingException e) {
		// }
		// }
		// }
		// }
		//
		// map.put("yh_nch", yh_nch);
		// int testDb = indexservice.testDb();
		// System.out.println(testDb);
		return "sale_index";
	}

}
