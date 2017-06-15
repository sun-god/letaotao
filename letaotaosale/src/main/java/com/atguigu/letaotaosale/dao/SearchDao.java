package com.atguigu.letaotaosale.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_SKU;

public interface SearchDao {

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(@Param("class_2_id") int class_2_id);

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2_attr_value(
			HashMap<String, Object> hashMap);

	OBJECT_T_MALL_SKU select_sku_detail(int sku_id);

	List<OBJECT_T_MALL_SKU> select_sku_list_by_spu_id(int spu_id);

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(
			HashMap<String, Object> hashMap);



}
