<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register</title>
	</head>
	<body>
		<form action="<%=basePath%>login/verify" method="post">
			手机：<input type="text" name="phone"/>
			密码：<input type="password" name="pwd"/>
			<input type="submit" value="登录" />
		</form>
		${exception}
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
			var path = "<%=path%>";
		</script>
	</body>
</html>