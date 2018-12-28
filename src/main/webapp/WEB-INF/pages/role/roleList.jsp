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

		<div class="roleList-div">
			<p>${msg }</p>

			<div class="roleList-div1">
				<table>
					<thead>
						<tr class="roleList-tr">
							<td>ID</td>
							<td>名字</td>
							<td>产线</td>
							<td>工序</td>
							<td>页面</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${compoundList != null }">
							<c:forEach items="${compoundList }" var="compound" varStatus="i">


								<tr class="roleList-tr"
									style='background: ${i.count % 2 == 1 ? "linear-gradient(to right, #66FFFF ,  #0066CC)" : "#fff"}' onclick="getRole('${compound.id }')">
									<td class="roleList-td">${compound.id }</td>
									<td class="roleList-td">${compound.name }</td>
									<td class="roleList-td">
										<div class="roleList-td-div">
											<c:forEach items="${compound.prodLineList }" var="prodLine">
												${prodLine.name }
											</c:forEach>
										</div>
									</td>

									<td class="roleList-td">
										<div class="roleList-td-div">
											<c:forEach items="${compound.prodProcessList }" var="prodProcess">
											${prodProcess.name }
											</c:forEach>
										</div>

									</td>
									<td class="roleList-td">
										<div class="roleList-td-div">
											<c:forEach items="${compound.pageList }" var="page">
										${page.name }
									</c:forEach>
										</div>
									</td>
								</tr>

							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="col-xs-4 changePage-div">
				<label>
					<a href="roleListByPage.do?pageNo=1">首页</a>
					<a href="roleListByPage.do?pageNo=${pageNo-1 }">上一页</a>
				</label>
			</div>
			<div class="col-xs-4 changePage-div">
				<p>${pageNo}页/共${totalpages }页</p>
			</div>
			<div class="col-xs-4 changePage-div">
				<label>
					<a href="roleListByPage.do?pageNo=${pageNo+1 }">下一页</a>
					<a href="roleListByPage.do?pageNo=${totalpages }">末页</a>
				</label>
			</div>
		</div>

<img class="zhuan" alt="" src="${contextPath }/image/user/a.gif">
	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>
<script type="text/javascript" src="${contextPath}/js/role.js"></script>
</html>