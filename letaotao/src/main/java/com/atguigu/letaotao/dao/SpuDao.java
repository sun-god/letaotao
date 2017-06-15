package com.atguigu.letaotao.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.letaotao.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.T_MALL_PRODUCT;



public interface SpuDao {

	int insert_product(T_MALL_PRODUCT spu);

	int insert_product_images(@Param("id") int id, @Param("images_name") List<String> images_name);

	int spu_publish(T_MALL_PRODUCT spu, List<String> images_name);

	void insert_attr(OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

	void insert_values(HashMap<String, Object> hashMap);

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(@Param("class_2_id") int class_2_id);

}
