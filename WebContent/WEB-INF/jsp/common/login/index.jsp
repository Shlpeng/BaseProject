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
		<title>index</title>
		<script src="<%=basePath%>assets/js/common/jquery-2.0.3.min.js"></script>
	</head>
	<body>
		<button onclick="delUser()">删除</button>
		<button onclick="updateUser()">更新</button>
	</body>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var path = "<%=path%>";
		function delUser(){
			var url = basePath + 'login/delUser';
        	$.ajax({
				url : url,
				type : "POST",
				data : {id: '806335ef-4de5-4ef3-8adf-f50f335e8e82'},
				success : function(data) {
					if(data.flag){
						alert("delete success");
					}else{
						alert("delete fail");
					}
				},
				error : function(data) {
					alert("error");
				}
        	});
		}
		function updateUser(){
			var url = basePath + 'login/updateUser';
        	$.ajax({
				url : url,
				type : "POST",
				success : function(data) {
					if(data.flag){
						alert("update success");
					}else{
						alert("update fail");
					}
				},
				error : function(data) {
					alert("error");
				}
        	});
		}
	</script>
</html>