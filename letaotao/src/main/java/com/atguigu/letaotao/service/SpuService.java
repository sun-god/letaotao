package com.atguigu.letaotao.service;

import java.util.List;

import com.atguigu.letaotao.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.T_MALL_PRODUCT;

public interface SpuService {
	

	/**
	 * 增加商品的信息
	 * @param spu
	 * @param images_name
	 * @return
	 */
	public int spu_publish(T_MALL_PRODUCT spu, List<String> images_name);

	/**
	 * 添加属性信息
	 * @param list_attr
	 * @param class_2_id
	 */
	public void addAttr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id);

	/**
	 * 根据2级分类的id获取属性
	 * @param class_2_id
	 * @return
	 */


	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id);

}
