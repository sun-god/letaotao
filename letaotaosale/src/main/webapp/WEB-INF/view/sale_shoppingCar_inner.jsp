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
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	function shoppingCar_inner_change(car_id,sku_id,shfxz,tjshl){
		if(shfxz==true){
			shfxz=1;
		}else if(shfxz==false){
			shfxz=0;
		}
		
 		$.post("shoppingCar_change.do",{car_id:car_id,sku_id:sku_id,shfxz:shfxz,tjshl:tjshl},function(data){
			$("#shoppingCar_inner").html(data);
		}); 
	}
	
/* 	$(function(){
		$("input").each(function(i,json){
			if(json.value=="1"){
				$(this).attr("checked",true);
			}else{
				$(this).attr("checked",false);
			}
		});
	}); */
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	 购物车列表 ：<br>
	<a href="javascript:shoppingCar_inner_change();">测试</a>
	<hr>
	<c:forEach items="${list_car}" var="car" varStatus="index">
		<input id="checkbox_${index.index}" type="checkbox" value="${car.shfxz}" ${car.shfxz=="1"?"checked":""} onclick="shoppingCar_inner_change(${car.id},${car.sku_id},this.checked,-1)"/>
		<img width="100px" alt="" src="upload/${car.shp_tp}">
		${car.sku_mch}
		<a href="javascript:shoppingCar_inner_change(${car.id},${car.sku_id},$('#checkbox_${index.index}').val(),${car.tjshl}+1);">+</a>
		${car.tjshl}
		<a href="javascript:shoppingCar_inner_change(${car.id},${car.sku_id},$('#checkbox_${index.index}').val(),${car.tjshl}-1);">-</a>
		${car.hj}<br>
	</c:forEach>
	<a href = "goto_order.do">结算</a>
</body>
</html>