<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" scope="page" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title>欢迎使用Zero</title>
<link rel="stylesheet" type="text/css" href="${path}/vision/css/base.css" />
</head>
<body>
	欢迎使用Zero开发平台。：）<a href="${path}/main/core/login.jsp">立即进入</a>
</body>
</html>