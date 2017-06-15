package com.atguigu.letaotao.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.letaotao.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.T_MALL_PRODUCT;
import com.atguigu.letaotao.service.SpuService;
import com.atguigu.letaotao.utils.MyUploadUtils;


@Controller
public class SpuController {

	@Autowired
	private SpuService service;
	
	/**
	 * 跳转到商品的发布页面
	 * @return
	 */
	@RequestMapping("/goto_spu_publish")
	public String doGoodsPublish() {
		
		return "goodspublish/spu_goods_publish";
	}
	
	/**
	 * 跳转到商品属性的展示页面
	 * @return
	 */
	@RequestMapping( "/display_goods_attr")
	public String displayGoodsAttr() {
		
		return "goodspublish/spu_display_goods_attr";
	}
	
	/**
	 * 跳转到商品属性的添加页面
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/do_spu_add_attr/{class_2_id}/{class_2_name}")
	public String addGoodsAttr(@PathVariable int class_2_id, @PathVariable String class_2_name,Map<String, Object> map) {
		map.put("class_2_id", class_2_id);
		map.put(class_2_name, class_2_name);
		
		return "goodspublish/spu_add_attr";
	}
	
	/**
	 * 属性添加成功之后重定向到商品属性的添加的页面
	 * @param list_attr
	 * @param class_2_id
	 * @param class_2_name
	 * @return
	 */
	@RequestMapping("/add_attr")
	public Object addAttr(MODEL_OBJECT_T_MALL_ATTR s, int class_2_id, String class_2_name) {
		 
		service.addAttr(s.getList_attr(),class_2_id);
		ModelAndView mv = new ModelAndView("redirect:/do_spu_add_attr/{class_2_id}/{class_2_name}.do");
		mv.addObject("class_2_id", class_2_id);
		mv.addObject("class_2_name",class_2_name);
		
		return mv;
	}
	
	/**
	 * 商品信息的添加页面
	 * @param spu
	 * @param files
	 * @return
	 */
	
	@RequestMapping(value = "spu_publish", method = RequestMethod.POST)
	public String spu_publish(T_MALL_PRODUCT spu, @RequestParam(value="files") MultipartFile[] files) {

		// 将图片上传到服务器指定位置
		List<String> images_name = MyUploadUtils.upload_imgs(files);
		// 调用业务层的商品发布的增删改查
		int i = service.spu_publish(spu, images_name);

		return "redirect:/goto_spu_publish/gongxi.do";
	}

	/**
	 * 商品信息添加成功之后跳转会商品的添加页面
	 * @param success
	 * @param map
	 * @return
	 */
	@RequestMapping("goto_spu_publish/{success}")
	public String goto_spu_publish(@PathVariable String success, ModelMap map) {
		map.put("success", success);
		return "goodspublish/spu_goods_publish";
	}
	
	/**
	 * 属性展示
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@RequestMapping("get_attr_list_by_class_2_id")
	public String get_attr_list_by_class_2_id(int class_2_id, ModelMap map) {

		// 根据二级分类id查询对应的分类属性集合
		List<OBJECT_T_MALL_ATTR> list_attr_value = service.get_attr_list_by_class_2_id(class_2_id);
		map.put("list_attr_value", list_attr_value);
		return "goodspublish/spu_attr_publish_inner";
	}
}
