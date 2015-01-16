<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" scope="page" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title>平台登陆</title>
<link rel="stylesheet" type="text/css" href="${path}/vision/css/base.css" />
<link rel="stylesheet" type="text/css" href="${path}/vision/css/login.css" />
<style type="text/css">

</style>
<script type="text/javascript" src="${path}/com/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
	$(function(){
		
		
		$("#username").focus(function(){
			if($(this).val()=="用户名"){
				$(this).val("").removeClass("font_gray");
			}	
		}).blur(function(){
			if($(this).val()==""){
				$(this).val("用户名").addClass("font_gray");	
			}
		}).addClass("font_gray");
		
		$("#password").focus(function(){
			if($(this).val()=="密码"){
				$(this).attr("type","password").val("").removeClass("font_gray");
			}
		}).blur(function(){
			if($(this).val()==""){
				$(this).attr("type","text").val("密码").addClass("font_gray");
			}
		}).addClass("font_gray");
	});
</script>
</head>
<body>
	<div class="wrapper">
		<div class="leftbox">
			<div class="zword">
				<b>Zero</b>集成开发平台
			</div>
		</div>
		<div class="loginform">	
			<div class="tit">
				<h1>用户登录</h1>
			</div>
			<div class="un">
				<i class="usr_icon"></i>
				<input type="text" name="username" id="username" value="用户名"  autocomplete="off"  />
			</div>
			<div class="pw">
				<i class="pwd_icon"></i>
				<input type="text" name="password" id="password" value="密码"  autocomplete="off"  />
			</div>
			<div class="lg">
				<input type="button" class="btn_login" value="登 录" />
			</div>
		</div>
	</div>
	
</body>
</html>