package com.atguigu.letaotao.serviceImp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.letaotao.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.letaotao.bean.T_MALL_PRODUCT;
import com.atguigu.letaotao.dao.SpuDao;
import com.atguigu.letaotao.service.SpuService;

@Service
public class SpuServiceImp implements SpuService {
	
	
	@Autowired
	SpuDao spuDao;

	public int spu_publish(T_MALL_PRODUCT spu, List<String> images_name) {
		// 将上传的第一张图片设置为头图
		spu.setShp_tp(images_name.get(0));
		// 插入商品信息
		int i = spuDao.insert_product(spu);

		// 根据商品表生成的id，批量插入商品图片
		int j = spuDao.insert_product_images(spu.getId(), images_name);

		return j;
	}

	public void addAttr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {
		
		for (int i = 0; i < list_attr.size(); i++) {
			OBJECT_T_MALL_ATTR object_T_MALL_ATTR = list_attr.get(i);
			object_T_MALL_ATTR.setFlbh2(class_2_id);
			spuDao.insert_attr(object_T_MALL_ATTR);

			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			hashMap.put("shxm_id", object_T_MALL_ATTR.getId());
			hashMap.put("class_2_id", class_2_id);
			hashMap.put("list_value", object_T_MALL_ATTR.getList_value());
			spuDao.insert_values(hashMap);
		}
	}

	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr_value = spuDao.select_attr_list_by_class_2_id(class_2_id);
		return list_attr_value;
	}
}
