<!DOCTYPE HTML>
<%@include file="base/base.jsp"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${contextPath }/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contextPath }/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<title>在此处插入标题</title>
</head>
<body>
	<div class="easyui-dialog" style="width:400px; height:200px"
		data-options="
        title:'My Dialog',
        iconCls:'icon-ok',
        onOpen:function(){}">
        dialog content.</div>
</body>
</html>
