<html>
<head>
<%@include file="../system/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system-wh.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/user/user.css" media="screen">
<link href="${contextPath }/plugins/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
<script src="${contextPath }/plugins/bootstrap/js/bootstrap-select.min.js"></script>
<title>mes展示后台管理</title>
</head>
<body>
	<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>

		<div class="th-div">
			<form action="update.do" method="post">
				<table class="userxx">
					<tr>
						<td colspan="2">
							<h1>${message }</h1>
						</td>
					</tr>
					<tr>
						<td class="text-right">用户ID：</td>
						<td>
							<%-- 	${user.userId} --%>
							<input type="text" class="user-text" name="userId" value="${user.userId}">
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
							<img id="myimage" src=' ${contextPath }${user.admin? "/image/user/y.png" : "/image/user/n.png"}'>
							<input type="hidden" name="admin" id="admin" value="${user.admin }">
						</td>
					</tr>
					<tr>
						<td class="text-right">
							<input type="submit" value="保存" class="lvan" style="width: 150px">
						</td>
						<td>
							<input type="button" class="lvan" value="删除该用户"
								onclick='location.href=("user/deleteUser.do?userId=${user.userId}")' style="width: 150px">
						</td>
					</tr>
				</table>
			</form>
			<div class="userRole">
				<c:if test="${compoundList != null }">
					<c:forEach items="${compoundList }" var="compound">
						<form action="updateUserRole.do?userId=${user.userId }&roleId=${compound.id }" method="post">
							<table>
								<tr>
									<td class="userRole-td" style="width:50px;">ID</td>
									<td class="userRole-td" style="width:100px;">名称</td>
									<td class="userRole-td">产线</td>
									<td class="userRole-td">工序</td>
									<td class="userRole-td">页面</td>
									<td class="userRole-td">操作</td>
								</tr>
								<tr>
									<td class="userRole-td">
									<span style="width:50px;">
											${compound.id }
									</span>
									</td>
									<td class="userRole-td">
										<input type="text" name="roleName" value="${compound.name }" style="width:90px; height:40px;border-radius:8px;">
									</td>
									<td class="userRole-td">
										<c:forEach items="${prodLineList }" var="prodLine">
											<c:if test="${compound.prodLineList.contains(prodLine) }">
												<input type="checkbox" name="prodLine" value="${prodLine.code }" checked>${prodLine.name }
											</c:if>

											<c:if test="${!compound.prodLineList.contains(prodLine) }">
												<input type="checkbox" name="prodProcess" value="${prodLine.code }">${prodLine.name }
											</c:if>
											<br>
										</c:forEach>
										<%-- 	<select class="selectpicker" multiple>
											<c:forEach items="${prodLineList }" var="prodLine">
												<option>${prodLine.name }</option>
											</c:forEach>
										</select>			 --%>
									</td>
									<td class="userRole-td">
										<c:forEach items="${prodProcessList }" var="prodProcess">
											<c:if test="${compound.prodProcessList.contains(prodProcess) }">
												<input type="checkbox" name="prodProcess" value="${prodProcess.code }" checked>${prodProcess.name }
										</c:if>

											<c:if test="${!compound.prodProcessList.contains(prodProcess) }">
												<input type="checkbox" name="prodProcess" value="${prodProcess.code }">${prodProcess.name }
										</c:if>
											<br>
										</c:forEach>
										<%-- <select class="selectpicker" multipleclass="selectpicker" multiple>
											<c:forEach items="${prodProcessList }" var="prodProcess">
												<option>${prodProcess.name }</option>
											</c:forEach>
										</select> --%>
									</td>
									<td class="userRole-td">
										<c:forEach items="${pageList }" var="page">
											<c:if test="${compound.pageList.contains(page) }">
												<input type="checkbox" name="page" value="${page.code }" checked>${page.name }
										</c:if>

											<c:if test="${!compound.pageList.contains(page) }">
												<input type="checkbox" name="page" value="${page.code }">${page.name }
										</c:if>
											<br>
										</c:forEach>
										<%-- <select class="selectpicker" multiple>
											<c:forEach items="${pageList }" var="page">
												<option value="${page.code }">${page.name }</option>
											</c:forEach>
										</select> --%>
									</td>
									<td class="userRole-td">
										<input type="submit" value="保存">
										<input type="button" value="删除">
									</td>
								</tr>

							</table>
						</form>
					</c:forEach>
				</c:if>
				<input type="button" value="添加新的权限" class="lvan">


			</div>



		</div>
	</div>


	<%@include file="../system/base/footer.jsp"%>
</body>
<script src="${contextPath}/js/user/user.js"></script>
</html>