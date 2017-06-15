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


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城首页</title>
</head>
<body>
	<jsp:include page="sale_index_header.jsp"></jsp:include>
	<ul id="index_class_1_ul" style="width:100px;"></ul><br>
	<ul id="index_class_2_ul" style="width:100px;"></ul><br>
</body>
<script type="text/javascript">
	$(function (){
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#index_class_1_ul").append("<li   onmouseover='index_get_class_2_by_class_1_id(this.value)' value="+json.id+">"+json.flmch1+"</li>");
			});
		});
	});
	
		var userNickName = cookieUtil("atguiguUser")
		var user = decodeURIComponent(userNickName)
		$("#userNickName").html(user);
		
		
	function index_get_class_2_by_class_1_id(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_class_2_ul").empty();
			$(data).each(function(i,json){
			    $("#index_class_2_ul").append("<li  value="+json.id+"><a href='get_sku_by_class_2/"+json.id+"/"+json.flmch2+"/.do' target='_blank'>"+json.flmch2+"</a></li>");
			});
		})
	}
</script>
</html>