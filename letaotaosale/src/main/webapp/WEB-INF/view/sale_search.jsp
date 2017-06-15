<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

 	function search_attr_value_down(attr_id){
 		search_attr_show(attr_id);
 		$("#param_"+attr_id).remove();
 		$("#a_"+attr_id).remove();
 		search_get_sku_by_class_2_attr_value();
 	}

	function search_attr_show(attr_id){
		$("#search_attr_show_"+attr_id).show();
	}
	
    function search_attr_hide(attr_id){
    	$("#search_attr_show_"+attr_id).hide();
    }

	function search_attr_value_up(attr_id, value_id, shx_mch) {
		$("#search_param").append("<a id='a_"+attr_id+"' href='javascript:search_attr_value_down("+attr_id+");'>"+shx_mch+"</a>");
		search_attr_hide(attr_id);

		//1用拼接数组的方式传递复合型参数 
		//$("#search_param").append("<input type='text' value='"+attr_id+"_"+value_id+"'>");

		//2 用json格式的字符串传递参数
		var json = "{\"shxm_id\":" + attr_id + ",\"shxzh_id\":" + value_id
				+ "}";
		$("#search_param").append("<input id='param_"+attr_id+"' type='hidden' value='"+json+"'>");

		// 调用异步查询sku集合的函数
		search_get_sku_by_class_2_attr_value();
	}

	function search_get_sku_by_class_2_attr_value() {

		var inputs = $("#search_param input");

		//1 用拼接数组的方式传递复合型参数
		//var arr = new Array();
		//$(inputs).each(function(i,json){
		//	arr[i]=json.value;
		//});

		//2 用json格式的字符串传递参数
		var model_attr_value = "class_2_id="+${class_2_id};
		$(inputs).each(function(i, json) {
			var json_input = json.value;
			var json_obj = jQuery.parseJSON(json_input);
			
			model_attr_value = model_attr_value+"&list_attr_value["+i+"].shxm_id="+json_obj.shxm_id+"&list_attr_value["+i+"].shxzh_id="+json_obj.shxzh_id;
		});
		
		model_attr_value = model_attr_value +"&order=" +$("#search_order").val();
	
		$.ajax({
			type:"post",
			url:"get_sku_by_class_2_attr_value.do",
			data:model_attr_value,
			success:function(data){
				$("#search_inner").html(data);
			}
		});
		
	}
	
	function search_order(order){
		
		//var indexof = $("#search_order").val().indexOf("desc");
		//if(indexof!=-1){
			//如果有给定的desc说明，之前是降序，order参数内容进行改变
		//}
		
		if($("#search_order").val()==order){
			order = order + " desc ";
		}
		$("#search_order").val(order);
		
		//调用异步检索函数
		search_get_sku_by_class_2_attr_value();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品检索页面</title>
</head>
<body>
<jsp:include page="sale_index_header.jsp"></jsp:include>
	<div id="search_param">导航：</div>
	<input id="search_order" type="hidden" name="order" value=" order by jg desc " />
	<hr>
	属性列表：
	<c:forEach items="${list_attr}" var="attr">
		<div id="search_attr_show_${attr.id}">
			${attr.shxm_mch}:
			<c:forEach items="${attr.list_value}" var="val">
				<a href="javascript:search_attr_value_up(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}');">${val.shxzh}${val.shxzh_mch}</a>
			</c:forEach>
			<hr>
		</div>
	</c:forEach>
	<a href="javascript:search_order(' order by jg ');">价格</a>
	<a href="javascript:search_order(' order by sku_xl ');">销量</a> 
	<a href="javascript:search_order(' order by sku.chjshj ');">上架时间</a>  
	<a href="javascript:;">评论数</a>
	<hr>
	
	<br>
	商品sku列表：
	<div id="search_inner">
		<jsp:include page="sale_search_inner.jsp"></jsp:include>
	</div>
</body>
</html>