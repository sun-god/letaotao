<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<script type="text/javascript">
	$(function(){
		// 取得一级分类的json静态数据
		$.getJSON("js/json/class_1.js",function(data){
			// 将json静态数据加载到一级分类下拉列表
			// for(i=0;i<data.length;i++){}
			$(data).each(function(i,json){
				$("#index_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
	});
	
	function index_select_class_2_by_class_1(class_1_id){
		// 获取一级分类id
		// $("#index_class_1_select option:selected").value;
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			// 将json静态数据加载到二级分类下拉列表
			// for(i=0;i<data.length;i++){}
			$("#index_class_2_select").empty(); 
			$(data).each(function(i,json){
				$("#index_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
		// 加载商标的下拉列表
		index_select_tm_by_class_1(class_1_id);
	}
	
	function index_select_tm_by_class_1(class_1_id){
		// 获取一级分类id
		// $("#index_class_1_select option:selected").value;
		$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
			// 将json静态数据加载到二级分类下拉列表
			// for(i=0;i<data.length;i++){}
			$("#index_tm_select").empty(); 
			$(data).each(function(i,json){
				$("#index_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
			});
		});
	}
	
	function index_image_click(index){
		$("#index_file_"+index).click();
	}
	
	function index_image_change(index){
		
		// 获得缩略图
		var image = $("#index_file_"+index)[0].files[0];
		
		// 将缩略图替换掉被点击的图片按钮
		var url = window.URL.createObjectURL(image);
		$("#index_image_"+index).attr("src",url);
		
		// 获取页面上的所有file对象
		var length = $(":file").length;
		
		if(length==(index+1)){
			// 调用图片追加方法
			index_image_append(index);
		}
	}
	
	function index_image_append(index){
		var img = '<img  id="index_image_'+(index+1)+'" width="100px" onclick="index_image_click('+(index+1)+')" src = "image/upload_hover.png" style="cursor:pointer;"/>';
		var input = '<input  style="display:none;" id="index_file_'+(index+1)+'" type="file" name="files" onChange="index_image_change('+(index+1)+')" /><br>';
		
		$("#index_image_div").append(img);
		$("#index_image_div").append(input);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息发布</title>
</head>
<body>
	${success}:商品信息发布<br>
	
	<form action="spu_publish.do" enctype="multipart/form-data" method="post">
		一级分类：<select name="flbh1" id="index_class_1_select" onChange="index_select_class_2_by_class_1(this.value)"></select><br>
		二级分类：<select name="flbh2" id="index_class_2_select" ></select><br>
		商标分类：<select name="pp_id" id="index_tm_select" ></select><br>
		<hr>
		商品spu名称：<input type="text" name="shp_mch"/><br>
		商品spu描述：<input type="text" name="shp_msh"/><br>
		商品图片组：<br>
		<div id="index_image_div">
			<img id="index_image_0" width="100px" onclick="index_image_click(0)" src = "image/upload_hover.png" style="cursor:pointer;"/>
			<input style="display:none;" id="index_file_0" type="file" name="files" onChange="index_image_change(0)" /><br>
		</div>
		<input type="submit" value="发布spu信息"/>
	</form>
</body>
</html>