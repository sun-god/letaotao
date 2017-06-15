package com.atguigu.letaotaosale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.letaotaosale.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotaosale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.letaotaosale.service.SearchService;


@Controller
public class SearchController {
	@Autowired
	SearchService searchservice;

	@RequestMapping("get_sku_detail")
	public String get_sku_detail(int sku_id, int spu_id, ModelMap map) {

		OBJECT_T_MALL_SKU object_sku = searchservice.get_sku_detail(sku_id);

		List<OBJECT_T_MALL_SKU> list_object_sku_other = searchservice.get_sku_list_by_spu_id(spu_id);

		map.put("object_sku", object_sku);
		map.put("list_object_sku_other", list_object_sku_other);

		return "sale_sku_detail";
	}

	/***
	 * 二级分类检索sku结果
	 * 
	 * @param class_2_id
	 * @return
	 */
	@RequestMapping("get_sku_by_class_2/{class_2_id}/{class_2_name}/{order}")
	public String get_sku_by_class_2(ModelMap map, @PathVariable int class_2_id, @PathVariable String class_2_name,
			@PathVariable String order) {

		// 检索sku列表集合
		List<OBJECT_T_MALL_SKU> list_object_sku = searchservice.get_sku_by_class_2_attr_value(class_2_id, null, order);

		// 查询分类属性列表
		List<OBJECT_T_MALL_ATTR> list_attr = searchservice.get_attr_value_by_class_2_id(class_2_id);

		map.put("list_attr", list_attr);
		map.put("list_object_sku", list_object_sku);

		return "sale_search";
	}

	/***
	 * 分类属性/属性值条件检索
	 * 
	 * @param class_2_id
	 * @param list_attr_value
	 * @return
	 */
	@RequestMapping(value = "get_sku_by_class_2_attr_value", method = RequestMethod.POST)
	public String get_sku_by_class_2_attr_value(ModelMap map, String order, int class_2_id,
			MODEL_T_MALL_SKU_ATTR_VALUE model_attr_value) {// ["1,2","3,4"]
		// List<T_MALL_SKU_ATTR_VALUE> list_attr_value = new
		// ArrayList<T_MALL_SKU_ATTR_VALUE>();
		// for (int i = 0; i < attr_values.length; i++) {
		// T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new
		// T_MALL_SKU_ATTR_VALUE();
		// String[] split = attr_values[i].split("_");
		// t_MALL_SKU_ATTR_VALUE.setShxm_id(Integer.parseInt(split[0]));
		// t_MALL_SKU_ATTR_VALUE.setShxzh_id(Integer.parseInt(split[1]));
		// list_attr_value.add(t_MALL_SKU_ATTR_VALUE);
		// }
		List<OBJECT_T_MALL_SKU> list_object_sku = searchservice.get_sku_by_class_2_attr_value(class_2_id,
				model_attr_value.getList_attr_value(), order);
		map.put("list_object_sku", list_object_sku);
		return "sale_search_inner";
	}

}