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

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<c:forEach items="${list_car}" var="car">
		<div class="one">
			<img src="upload/${car.shp_tp}" width="70px"/>
			<span class="one_name">
				${car.sku_mch}
			</span>
			<span class="one_prece">
				<b>${car.sku_jg}</b><br />
				&nbsp;&nbsp;删除
			</span>
		</div>
	</c:forEach>	
	<div class="gobottom">
		共<span>${count}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
		共计￥<span>${sum}</span>
		<button class="goprice">去购物车</button>
	</div>


</body>
</html>