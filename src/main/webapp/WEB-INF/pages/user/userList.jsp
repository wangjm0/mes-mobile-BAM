<%@include file="../system/base/base.jsp"%>

<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctgf.wxmes.*, java.util.List,java.lang.Integer"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath }/css/system.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/index.css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/action.css" media="screen">
<script src="${contextPath}/js/user/userList.js"></script>
<title>mes展示后台管理</title>
<script type="text/javascript">
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
</head>
<body >
	<%@include file="../system/base/header.jsp"%>
	<div class="container">
		<%@include file="../system/base/action.jsp"%>

		<div class="first-div">
			<form method="post" action="">
				<%
					List<User> list = (List<User>) session.getAttribute("list");
					int pageNo = (Integer) session.getAttribute("pageNo");
					int totalpages = (Integer) session.getAttribute("totalpages");
				%>
				<table class="userList">
					<!-- <thead>
						<tr>
							<td>
								<input type="text" placeholder="姓名" class="findUser" name="findName" id="findName" />
								<input type="button" class="button" value="搜索" onclick="searchByName()" />
							</td>
						</tr>
					</thead> -->
					<thead>
						<tr class="list" >
							<td>姓名</td>
							<td>用户ID</td>
						</tr>
					</thead>
					<c:forEach items="${list }" var="user" varStatus="i">
						<tr class="list" style='background-color: ${i.count % 2 == 1 ? "#73B8EF" : "#fff"}' onclick="getUser(${user.userId})">
							<td>
								<%-- <input class="user" name="userId" type="button" value="${user.userId}"> --%>

								<span>${user.userName }</span>

							</td>
							<td>
								<%-- <input class="user" name="userId" type="button" value="${user.userId}"> --%>

								<span>${user.userId }</span>

							</td>
						</tr>
					</c:forEach>
				</table>



			</form>
		</div>
		<div class="col-xs-4 second-div">
			<label>
				<a href=" userList.do?pageNo=1" >首页</a>
				<a href="userList.do?pageNo=${pageNo-1 }">上一页</a>
			</label>
		</div>
		<div class="col-xs-4 second-div">
			<p>${pageNo}页/共${totalpages }页</p>
		</div>
		<div class="col-xs-4 second-div">
			<label>
				<a href="userList.do?pageNo=${pageNo+1 }" >下一页</a>
				<a href="userList.do?pageNo=${totalpages }" >末页</a>
			</label>
		</div>

	</div>
	<%@include file="../system/base/footer.jsp"%>
</body>

<script type="text/javascript">
        $(function()
        {
            $("tr td").not("tr td:first-child").bind('click',function()
            {
                alert($(this).text());
            })
        })
    </script>
</html>