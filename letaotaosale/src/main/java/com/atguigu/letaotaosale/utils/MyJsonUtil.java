package com.atguigu.letaotaosale.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.letaotaosale.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MyJsonUtil {

	/**
	 * object_to_json
	 * Gson
	 * @param t
	 * @return
	 */
	public static <T> String object_to_json(T t) {
		Gson gson = new Gson();
		String json = "";
		if (!t.getClass().getSimpleName().equals("String")) {
			json = new String(gson.toJson(t));
		} else {
			json = (String) t;
		}

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * json_to_object
	 * Gson
	 * @param json
	 * @return
	 */
	public static  List<T_MALL_SHOPPINGCAR> json_to_object(String json){
		Gson gson = new Gson();
		TypeToken<List<T_MALL_SHOPPINGCAR>> typeToken = new TypeToken<List<T_MALL_SHOPPINGCAR>>() {
		};
		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<T_MALL_SHOPPINGCAR> fromjson = (List<T_MALL_SHOPPINGCAR>)gson.fromJson(json, typeToken.getType());
		return fromjson;
	}
	

}
