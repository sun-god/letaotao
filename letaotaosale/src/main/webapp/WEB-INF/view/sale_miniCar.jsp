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
	function sale_show_car(){
		$.post("get_miniCar.do",function(data){
			$("#miniCar_inner").html(data);
		});
		$("#miniCar_show").show();
	}
	function sale_hide_car(){
		$("#miniCar_show").hide();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>迷你购物车</title>
</head>
<body>
		<div class="card">
			<a href="goto_shoppingCar.do" onmouseover="sale_show_car()" onmouseout="sale_hide_car()" target="_blank">购物车<div class="num">0</div></a>
			
			<!--购物车商品-->
			<div class="cart_pro"  id = "miniCar_show" style="display:none;">
				<h6>迷你购物车</h6>
				<div id="miniCar_inner">
				</div>
			</div>
		
		</div>
		


</body>
</html>