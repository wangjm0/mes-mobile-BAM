<%@include file="base/base.jsp"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mes后台管理</title>
<link rel="stylesheet" href="${contextPath}/css/login.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
</head>
<body>
	<div class="container">
	<%@include  file="base/header.jsp"%>
	<div class="login-action">
			<form action="${contextPath }/login/isLogin.do" method="post">
			<table>
				<tr><td><h1>${message }</h1></td></tr>
				<tr><td class="center"><div class="radius"><img alt="用户" src="${contextPath }/image/login/input-name.png"/><input type="text" id="username" name="username" placeholder="请输入用户名"/></div></td></tr>
				<tr><td class="center"><div class="radius"><img alt="密码" src="${contextPath }/image/login/input-password.png"/><input type="password" id="password" name="password" placeholder="请输入密码"/></div></td></tr>
				<tr><td class="center"><div class="radius"><input type="submit" value="登录" class="loginbam"/></div></td></tr>
			</table>
		</form>
		<a class="findPassword" href="/getPassword">忘记密码</a>
	</div>
	<%@include file="base/footer.jsp" %>
	</div>

</body>
<script src="${contextPath}/js/login.js" ></script>
</html>