<%@include file="base/base.jsp"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>mes后台管理</title>
<link rel="stylesheet" href="${contextPath}/css/login.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
</head>
<body>
	<%@include file="base/header.jsp"%>
	<div class="container">


		<div class="login-div">
			<div class="login-d-top">
				<a href="javascript:;" class=" top-a saoma"> 扫码登录</a>
				<a href="javascript:;" class="top-a mima"> 密码登录</a>
			</div>
			<ul>
				<li class="login-d-saoma" style="display: none;">
					<div class="erweima">
						<div class="qrcode">
							<div id="output" style="width: 100%; position: relative">
							
							</div>
						</div>
					</div>
					<div style="height: 70px">
						<p>微信扫码 安全便捷</p>
					</div>
				</li>
				<li class="login-d-mima" style="display: block;">
					<form action="login/isLogin.do" method="post">
						<table>
							<tr>
								<td>
									<h1 class="message">${message }</h1>
								</td>
							</tr>
							<tr>
								<td class="center">
									<div class="radius">
										<img alt="用户" src="${contextPath }/image/login/input-name.png" />
										<input class="lo" type="text" id="username" name="username" placeholder="请输入用户名" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="center">
									<div class="radius">
										<img alt="密码" src="${contextPath }/image/login/input-password.png" />
										<input class="lo" type="password" id="password" name="password" placeholder="请输入密码" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="center">
									<div class="radius">
										<input type="submit" class="loginbam" value="登录" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					<a class="findPassword" href="/getPassword">忘记密码</a>
				</li>
			</ul>

		</div>

	</div>


	<div class="fixed-hidden">
		<div class="message">
			<p id="message"></p>
			<button id="check" class="check" onclick="subClick()">确认</button>
		</div>
	</div>

	<div class="hidden-createUser">
		<form action="user/add.do" method="post">
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
			</table>
			<input type="submit" class="createUser check" value="提交" />
		</form>
	</div>
	<div class="hidden-getAdmin">
		<form method="post" action="user/setAdmin.do">

			<table class="userList">
				<thead>
					<tr>
						<td>
							<input type="text" placeholder="姓名" class="findUser" name="findName" id="findName" />
							<input type="button" class="button" value="搜索" onclick="searchByName()" />
						</td>
					</tr>
				</thead>
				<c:forEach items="${list }" var="user" varStatus="i">
					<tr>
						<td class="list" style='background-color: ${i.count % 2 == 1 ? "#ffffff" : "#66CCCC"}'>
							<input class="user" name="userId" type="radio" value="${user.userId}">
							${user.userName } ${user.userId }
						</td>
					</tr>
				</c:forEach>
			</table>


			<div class="col-xs-4">
				<label>
					<a href="" onclick="toPage(1,${pageNo},${totalpages })">首页</a>
					<a href="" onclick="toPage(2, ${pageNo},${totalpages })">上一页</a>
				</label>
			</div>
			<div class="col-xs-4">
				<p>${pageNo}页/共${totalpages }页</p>
			</div>
			<div class="col-xs-4">
				<label>
					<a href="" onclick="toPage(3, ${pageNo},${totalpages })">下一页</a>
					<a href="" onclick="toPage(4, ${pageNo},${totalpages })">末页</a>
				</label>
			</div>
			<input type="submit" value="确认" class="check" />
		</form>
	</div>
	<%@include file="base/footer.jsp"%>
</body>
<script src="${contextPath}/js/login.js"></script>

<!--企业微信扫码登录js  -->
<script type="text/javascript" src="http://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js"></script>
<script type="text/javascript">
window.WwLogin({
    "id" : "output",  
    "appid" : "ww8b92534609022c60",
    "agentid" : "1000014",
    "redirect_uri" :"http://public.ctgf.com:65516/mes-mobile-BAM/login/getUser.do",
/*     "state" : "",
    "href" : "", */
});
</script>
<script type="text/javascript">
function subClick()
{
	fixed.hide();
	if(!bn)
	{
		getAdmin.hide();
		createUser.show();
	}
	else
	{
		createUser.hide();
		getAdmin.show();
	 	getUserListByPage(1); 
		
	}
}
function toPage(i,pageNo,totalpages)
{
	switch(i){
	case 1:
		pageNo = 1;
		getUserListByPage(1); 
		break;
	case 2:
		pageNo = (pageNo <= 1) ? pageNo : (pageNo - 1);
		getUserListByPage(pageNo);
		break;
	case 3:
		pageNo = (pageNo >=totalpages)?totalpages:(pageNo+1);
		getUserListByPage(pageNo);
		break;
	case 4:
		pageNo = totalpages;
		getUserListByPage(totalpages);
		break;
	default:
		break;
	}
		
}
</script>

</html>