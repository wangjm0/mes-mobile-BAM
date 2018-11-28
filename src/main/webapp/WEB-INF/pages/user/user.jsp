<html>
<head>
<%@include file="../system/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/index.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<title>mes展示后台管理</title>
</head>
<body>
	<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>

		<div class="th-div">
			<%
				User user = (User) session.getAttribute("user");
		
			%>
			<div class="tb-div">
				<form action="user/save.do" method="post">
				<table>
					<tr >
						<td class="text-right">用户ID：</td>
						<td>
							<input type="text" class="user-text"  name="userId"value="${user.userId}">
						</td>
					</tr>
					<tr>
						<td class="text-right">用户姓名：</td>
						<td>
							<input type="text" class="user-text" name="username" value="${user.userName }">
						</td>
					</tr>
					<tr>
						<td class="text-right">用户密码：</td>
						<td>
							<input type="text" class="user-text" name="password" value="${user.passWord }">
						</td>
					</tr>
					<tr>
						<td class="text-right">用户为管理员？</td>
						<td onclick="changeAdmin()">
						<img id="myimage" src=' ${user.admin?"image/user/y.png" :"image/user/n.png"}'>
						<input type="hidden" name="admin" id="admin" value="${user.admin }">
						</td>
					</tr>
					<tr><td class="text-right" colspan="2"><input type="submit" value="保存" class="check"></td></tr>
				</table>
			</form>
			</div>
			<div class="sxjz">
				<input type="button" class="lvan" value="删除该用户">
				<input type="button" class="lvan" value="创建新用户">
			</div>
			
		</div>


	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>
<script type="text/javascript">
function changeAdmin()
{
	var admin =$("#admin");
	var element = document.getElementById('myimage'); 
	var text =window.location.protocol +"//"+ window.location.host + contextPath+'/image/user/y.png';
	if(element.src ==text)
	{
		element.src = "image/user/n.png";
		admin.attr("value", false);
		admin.val = false;
	}
	else
	{
		element.src = "image/user/y.png";
		admin.attr("value", true);
	}
}
</script>
</html>