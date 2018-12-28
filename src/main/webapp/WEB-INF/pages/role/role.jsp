<html>
<head>
<%@include file="../system/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.ctgf.wxmes.entity.*, java.util.List,java.lang.Integer"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system-wh.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/role/roleCss.css" media="screen">
<title>mes展示后台管理</title>
</head>
<body>
	<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>
		<div class="updateRole-div">
			<form action="updateRole.do?id=${compound.id }" method="post">
				<c:if test="${compound != null }">
					<div class="col-xs-6 ">
					<h4>ID: ${compound.id }</h4>
				
						name:<br>
						<input type="text" name="name" value="${compound.name }">
					</div>
					<div class="col-xs-6">
						<h4>产线</h4>
						<div class="role-div">
						<c:forEach items="${prodLineList }" var="prodLine">
							<c:if test="${compound.prodLineList.contains(prodLine) }">
								<input type="checkbox"  name="prodLine" value="${prodLine.code }" checked>${prodLine.name }
							</c:if>
							
							<c:if test="${!compound.prodLineList.contains(prodLine) }">
								<input type="checkbox"  name="prodLine" value="${prodLine.code }" >${prodLine.name }
							</c:if><br>
						</c:forEach>
						</div>
					</div>
					<div class="col-xs-6 " >
						<h4>工序</h4>
						<div class="role-div">
					<c:forEach items="${prodProcessList }" var="prodProcess">
							<c:if test="${compound.prodProcessList.contains(prodProcess) }">
								<input type="checkbox"  name="prodProcess" value="${prodProcess.code }" checked>${prodProcess.name }
							</c:if>
							
							<c:if test="${!compound.prodProcessList.contains(prodProcess) }">
								<input type="checkbox"  name="prodProcess" value="${prodProcess.code }" >${prodProcess.name }
							</c:if><br>
						</c:forEach>
					</div>
					</div>
					<div class="col-xs-6 ">
					
						<h4>页面</h4>
						<div class="role-div">
					<c:forEach items="${pageList }" var="page">
							<c:if test="${compound.pageList.contains(page) }">
								<input type="checkbox"  name="page" value="${page.code }" checked>${page.name }
							</c:if>
							
							<c:if test="${!compound.pageList.contains(page) }">
								<input type="checkbox"  name="page" value="${page.code }" >${page.name }
							</c:if><br>
							</c:forEach>
							</div>
					</div>
				</c:if>
				<input  class="col-xs-4 anniu" type="submit" value="保存">
				<input class="col-xs-4 anniu" class="col-xs-4" class="col-xs-4" type="button" value="删除此角色" onclick='location.href=("deleteRole.do?id=${compound.id}")'>
			</form>
		</div>
	<img class="pt" alt="" src="${contextPath }/image/role/xm1.gif">
	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>
</html>