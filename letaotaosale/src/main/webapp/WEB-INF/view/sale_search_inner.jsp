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
	function a(){}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	<c:forEach items="${list_object_sku}" var="object_sku">
		<div style="width:300px;height:300px;float:left;" >
			<img src="upload/${object_sku.spu.shp_tp}" width="150px" height="150px"/><br>
			<a href="get_sku_detail.do?sku_id=${object_sku.id}&spu_id=${object_sku.spu.id}">${object_sku.sku_mch}<br>
			${object_sku.jg}<br>
			${object_sku.kc}<br>
		</div>
	</c:forEach>
</body>
</html>