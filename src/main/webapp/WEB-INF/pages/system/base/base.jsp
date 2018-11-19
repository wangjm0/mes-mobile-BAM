<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@taglib prefix="spring"  uri="http://www.springframework.org/tags" %> --%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@taglib  prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%request.setAttribute("contextPath", request.getContextPath());%>
<script type="text/javascript">var contextPath = '${contextPath}';</script>
<!-- css -->
<link rel="stylesheet" href="${contextPath}/plugins/bootstrap/css/bootstrap.min.css" type="text/css">
<!-- jquery -->
<script type="text/javascript" src="${contextPath}/plugins/jquery/jquery-3.2.1.min.js"></script>
<!-- bootatrap -->
<script type="text/javascript" src="${contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- vue -->
<script type="text/javascript" src="${contextPath}/plugins/vue.js/vue.min.js"></script>
<!-- ajax -->
<script type="text/javascript" src="${contextPath}/js/common/ajax.js"></script>