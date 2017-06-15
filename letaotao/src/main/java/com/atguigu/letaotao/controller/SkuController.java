package com.atguigu.letaotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.letaotao.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.letaotao.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.T_MALL_PRODUCT;
import com.atguigu.letaotao.bean.T_MALL_SKU;
import com.atguigu.letaotao.service.SkuService;
import com.atguigu.letaotao.service.SpuService;


@Controller
public class SkuController {

	@Autowired
	SkuService skuService;

	@Autowired
	SpuService attrServiceImp;

	@RequestMapping("add_sku")
	public ModelAndView add_sku(MODEL_T_MALL_SKU_ATTR_VALUE list_attr_value, int shp_id, T_MALL_SKU sku) {
		// int testDb = indexservice.testDb();

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_sku_publish/.do");

		sku.setShp_id(shp_id);
		skuService.add_sku(list_attr_value.getList_attr_value(), sku);

		return modelAndView;
	}

	@RequestMapping("sku_get_attr_list_by_class_2_id")
	public String sku_get_attr_list_by_class_2_id(int class_2_id, ModelMap map) {

		// 根据二级分类id查询对应的分类属性集合
		List<OBJECT_T_MALL_ATTR> list_attr_value = attrServiceImp.get_attr_list_by_class_2_id(class_2_id);
		map.put("list_attr_value", list_attr_value);
		return "manager_sku_publish_inner";
	}

	@ResponseBody
	@RequestMapping("get_spu_by_ppid_class2id")
	public List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id) {
		List<T_MALL_PRODUCT> list_product = skuService.get_spu_by_ppid_class2id(class_1_id, class_2_id, pp_id);
		return list_product;
	}

	@RequestMapping("/goto_sku_publish")
	public String goto_sku_publish() {
		// int testDb = indexservice.testDb();
		return "goodspublish/manager_sku_publish";
	}

}
