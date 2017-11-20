<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/keephealth/js/jquery-1.11.3.min.js"></script>
<title>keep health</title>
</head>
<body>
	keep health!
	${size }
	<c:forEach var="user" items="${list }">
		<h1>${user.id } : ${user.realname }</h1>
	</c:forEach>
	<div>
		<div>
			<span>用户名</span>
			<input type="text" name="username" id="username" value="admin"/>
		</div>
		<div>
			<span>密码</span>
			<input type="password" name="password" id="password" value="123"/>
		</div>
		<div>
			<input type="button" id="login" value="登陆"/>
			<input type="reset" value="重置"/>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#login').click(function(e){
				$.ajax({
					url : '<%=request.getContextPath() %>/login',
					method : 'post',
					data:{username: $('#username').val(),password:$('#password').val()},
					dataType : 'json',
					success : function(resp){
						console.log(resp.success);
					},
					error : function(resp){
						
					}
				});
			});
		});
	</script>
</body>
</html>