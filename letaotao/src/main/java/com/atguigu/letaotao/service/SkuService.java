package com.atguigu.letaotao.service;

import java.util.List;

import com.atguigu.letaotao.bean.T_MALL_PRODUCT;
import com.atguigu.letaotao.bean.T_MALL_SKU;
import com.atguigu.letaotao.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {

	List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id);

	void add_sku(List<T_MALL_SKU_ATTR_VALUE> list_attr_value, T_MALL_SKU sku);

}
