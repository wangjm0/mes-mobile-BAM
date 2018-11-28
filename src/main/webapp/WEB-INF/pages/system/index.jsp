<%@include file="base/base.jsp"%>

<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/index.css" media="screen">
<title>mes展示后台管理</title>
</head>
<body>
<%@include file="base/header.jsp"%>
	<div class="container">
		
		<%@include file="base/action.jsp"%>
		<div class="div-actions">
			<div class=" div-action  bg1">
				<a class="sub-action" href="userList.do?pageNo=1">
					<span >
						<img src="${contextPath }/image/index/person.png" />
						用户列表
					</span>
				</a>
			</div>
				<div class=" div-action  bg1">
				<a class="sub-action" href="userList.do">
					<span >
						<img src="${contextPath }/image/index/role.png" />
						角色管理
					</span>
				</a>
			</div>
			<div class=" div-action bg2">
				<a class="sub-action">
					<span >
						<img src="${contextPath }/image/index/power.png" />
						用户权限
					</span>
				</a>
			</div>
			<!-- 
			<div class=" div-action bg3">
				<a class="sub-action  bg3">？？</a>
			</div> -->
			<div class=" div-action bg4">
			<a class="sub-action">
					<span >
				<img src="${contextPath }/image/index/log.png" />
				查看日志
				</span>
				</a>
			</div>
		</div>

	</div>
	<%@include file="base/footer.jsp"%>
</body>
</html>