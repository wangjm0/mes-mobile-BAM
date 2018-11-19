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
			<form action="login/isLogin.do" method="post">
				<table>
				<tr><td><h1 class="message">${message }</h1></td></tr>
				<tr><td class="center"><div class="radius"><img alt="用户" src="${contextPath }/image/login/input-name.png"/><input type="text" id="username" name="username" placeholder="请输入用户名"/></div></td></tr>
				<tr><td class="center"><div class="radius"><img alt="密码" src="${contextPath }/image/login/input-password.png"/><input type="password" id="password" name="password" placeholder="请输入密码"/></div></td></tr>
				<tr><td class="center"><div class="radius"><input type="submit" class="loginbam" value="登录"/></div></td></tr>
			</table>
			</form>
		<a class="findPassword" href="/getPassword">忘记密码</a>
	</div>
	<%@include file="base/footer.jsp" %>
	</div>
	
	<div class="fixed-hidden">
		<div class="message"><p id="message"></p> <input type="button"  class="check"value="确认" onclick="click();"/></div>
	</div>
	
	<div class="hidden-createUser">
		<form action="" method="post">
			<table>
				<tr><td class="font-left">用户名：</td><td></td></tr>
				<tr><td class="font-left">密码：</td><td></td></tr>
				<tr><td class="font-left">确认密码：</td><td ></td></tr>
				<tr><td ><input type="checkbox" value="默认为管理员" checked="checked"/></td></tr>
			</table>
			<input type="submit"  class="createUser" value="提交"/>
		</form>
	</div>
	<div class="hidden-getAdmin"></div>
</body>
<script src="${contextPath}/js/login.js" ></script>
</html>