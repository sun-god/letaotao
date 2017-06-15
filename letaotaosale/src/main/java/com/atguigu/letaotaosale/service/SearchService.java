package com.atguigu.letaotaosale.service;

import java.util.List;

import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.letaotaosale.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchService {

	/**
	 * 通过2级分类的id查询2级分类属性
	 * @param class_2_id
	 * @return
	 */
	List<OBJECT_T_MALL_ATTR> get_attr_value_by_class_2_id(int class_2_id);

	/**
	 * 查询2级分类属性值
	 * @param class_2_id
	 * @param list_attr_value
	 * @param order
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String order);

	/**
	 * 通过库存id查询库存的详细信息
	 * @param sku_id
	 * @return
	 */
	OBJECT_T_MALL_SKU get_sku_detail(int sku_id);

	/**
	 * 通过商品发布的id查询商品的信息
	 * @param spu_id
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> get_sku_list_by_spu_id(int spu_id);

}
