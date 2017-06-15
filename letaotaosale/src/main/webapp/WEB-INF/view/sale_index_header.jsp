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
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript">
	$(function (){
		var user = cookieUtil("atguiguUser");
		var user_url = decodeURIComponent(user);
		//var json = jQuery.parseJSON(user_url);
		$("#index_user_nch").html(user_url);
	});

	function cookieUtil(key){
		var cookie = document.cookie;
		var cookies = cookie.split(";");
		var re = "";
		for(i=0;i<cookies.length;i++){
			var values = cookies[i].split("=");//atguiguUser=%@31%%#%  test=test
			if(values[0]==key){
				re = values[1];	
			}
		}
		return re;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	<div class="top">
		<div >
				<c:if test="${user==null}">
					<a id="userNickName"></a><a href="goto_login.htm" target="_blank">请登录</a><a href="javascript:;">请注册</a>
				</c:if>
				<c:if test="${user!=null}">
					<a href="logout.htm">欢迎${user.yh_nch} 注销</a><a href="javascript:;">我的订单</a>
				</c:if>
		</div>
	</div>

</body>
</html>