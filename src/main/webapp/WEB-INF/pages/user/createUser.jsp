<html>
<head>
<%@include file="../system/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system-wh.css" media="screen">
<title>mes展示后台管理</title>
</head>
<body>
		<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>
		
		<div class="createUser">
				<form action="user/createUser.do" method="post">
			<table>
			<tr>
						<td colspan="2">
							<h1 class="message">${msg }</h1>
						</td>
					</tr>
				<tr class="create">
					<td class="font-left">用户ID：</td>
					<td>
						<input class="create" type="text" name="userId" />
					</td>
				</tr>
				<tr class="create">
					<td class="font-left">用户名：</td>
					<td>
						<input class="create" type="text" name="username2" />
					</td>
				</tr>
				<tr class="create">
					<td class="font-left">密码：</td>
					<td>
						<input class="create" type="password" name="password1" />
					</td>
				</tr>
				<tr class="create">
					<td class="font-left">确认密码：</td>
					<td>
						<input class="create" type="password" name="password2" />
					</td>
				</tr>
				<tr class="create">
					<td colspan="2">
						<input type="checkbox" checked="checked" class="checkbox" value="1" name="admin">
						默认为管理员
					</td>
				</tr>
				<tr  class="create"><td colspan="2">	<input type="submit" class="createUser" value="提交" /></td></tr>
				<tr  class="create"><td colspan="2">	<input type="reset"  class="createUser" value="重置" /></td></tr>
			</table>
		
		</form>
		</div>
		

	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>
</html>