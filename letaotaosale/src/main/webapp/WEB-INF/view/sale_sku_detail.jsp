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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a(){}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	${object_sku.sku_mch}<br>
	${object_sku.jg}<br>
	<img src ="upload/${object_sku.shp_tp}" width="200px"/><br>
	<hr>
	<c:forEach items="${list_object_sku_other}" var="other">
		<a href = "get_sku_detail.do?sku_id=${other.id}&spu_id=${other.shp_id}" >${other.sku_mch}</a><br>
	</c:forEach>
	<hr>
	<form action="add_car.do" method="post">
		<input type="hidden" name="sku_mch" value = "${object_sku.sku_mch}"/>
		<input type="hidden" name="sku_jg" value = "${object_sku.jg}"/>
		<input type="hidden" name="tjshl" value = "1"/>
		<input type="hidden" name="shfxz" value = "1"/>
		<input type="hidden" name="hj" value = "${object_sku.jg}"/>
		<input type="hidden" name="shp_id" value = "${object_sku.shp_id}"/>
		<input type="hidden" name="sku_id" value = "${object_sku.id}"/>
		<input type="hidden" name="shp_tp" value = "${object_sku.spu.shp_tp}"/>
		<input type="hidden" name="kcdz" value = "${object_sku.kcdz}"/>
		<input type="submit" value="添加购物车"/>
	</form>
	<hr>
	<c:forEach items="${object_sku.list_attr_value_name}" var="a_v_n">
		${a_v_n.attr_name}:${a_v_n.value_name}<br>
	</c:forEach>
	<hr>
	
	${object_sku.spu.shp_msh}<br>
	<c:forEach items="${object_sku.list_image}" var="img">
		<img src ="upload/${img.url}" width="200px"/><br>
	</c:forEach>
	
	
</body>
</html>