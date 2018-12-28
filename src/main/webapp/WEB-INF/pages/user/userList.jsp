<%@include file="../system/base/base.jsp"%>

<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/system-wh.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<script src="${contextPath}/js/user/userList.js"></script>
<title>mes展示后台管理</title>
<script type="text/javascript">
	function toPage(i, pageNo, totalpages)
	{
		switch (i)
		{
		case 1:
			pageNo = 1;
			getUserListByPage(1);
			break;
		case 2:
			pageNo = (pageNo <= 1) ? pageNo : (pageNo - 1);
			getUserListByPage(pageNo);
			break;
		case 3:
			pageNo = (pageNo >= totalpages) ? totalpages : (pageNo + 1);
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
</head>
<body>
	<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>
	
		<div class="findUBName">
			
			<form action="${contextPath }/user/findUserlistByName.do">
				<input type="text" name="username" placeholder="">
			<input type="submit" value="姓名查找" >
			</form>
			
		</div>
		<img class="gxq" alt="" src="${contextPath }/image/user/d.gif">
		<div class="first-div">

			<form method="post" action="">
				<table class="userList">
					<thead>
						<tr class="list">
							<td>用户ID</td>
							<td>姓名</td>
							<td>角色</td>
							<td>管理员</td>
						</tr>
					</thead>
					<c:forEach items="${list }" var="user" varStatus="i">
						<tr class="list" style='background-color: ${i.count % 2 == 1 ? "#6eb9f4a6" : "#fff"}'
							 onclick='location.href=("user/findByUserId.do?userId=${user.userId}")'>
							<td>${user.userId }</td>
							<td>${user.userName }</td>
							<td>
								<c:forEach items="${compoundMap }" var="map">
									<c:if test="${map.key == user.id }">

										<c:forEach items="${map.value }" var="compound">

											<c:forEach items="${prodLineStr }" var="s1">
												<c:forEach items="${prodProcessStr }" var="s2">
													<c:forEach items="${pageStr }" var="s3">
														<c:if test="${s1.key == user.id && s1.key == s2.key && s2.key == s3.key }">
															<a href="#" class="role" data-toggle="tooltip" data-placement="bottom"
																title="ID:${compound.id }&#13;名称：${compound.name}&#13;产线：${s1.value } &#13;工序：${s2.value }&#13;页面：${s3.value }">${compound.name }</a>

														</c:if>

													</c:forEach>
												</c:forEach>
											</c:forEach>


										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
							<td>
								<c:if test="${user.admin }">
									<img alt="是" src="${path }/${contextPath }/image/user/y.png ">
								</c:if>
								<c:if test="${!user.admin }">
									<img alt="否" src="${path }/${contextPath }/image/user/n.png ">
								</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>



			</form>
				<div class="col-xs-4 second-div">
			<label>
				<a href=" userList.do?pageNo=1">首页</a>
				<a href="userList.do?pageNo=${pageNo-1 }">上一页</a>
			</label>
		</div>
		<div class="col-xs-4 second-div">
			<p>第${pageNo}页/共${totalpages }页</p>
		</div>
		<div class="col-xs-4 second-div">
			<label>
				<a href="userList.do?pageNo=${pageNo+1 }">下一页</a>
				<a href="userList.do?pageNo=${totalpages }">末页</a>
			</label>
		</div>
		</div>
	

	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>
</html>