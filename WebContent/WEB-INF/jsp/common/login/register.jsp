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
		<form action="<%=basePath%>login/register" method="post">
			昵称：<input type="text" name="nickName"/>
			密码：<input type="password" name="pwd"/>
			确认密码：<input type="password" id="pwd2"/>
			手机：<input type="text" name="phone"/>
			邮箱：<input type="text" name="email"/>
			<input type="submit" value="注册" />
		</form>
		${exception}
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
			var path = "<%=path%>";
		</script>
	</body>
</html>