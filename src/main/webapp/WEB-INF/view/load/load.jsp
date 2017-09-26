<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="../favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>登录</title>
</head>
<body>
  <center>
     <h1>登录</h1>  
    <form action="/test/user/load" name="userForm" method="post">  
        <input type="hidden" name="id" value="${id}">  
        姓名：<input type="text" readonly= "true " name="userName" value="${name}">  
        <br>
        密码：<input type="password" name="passWord">  
        <br>
        <input type="submit" value="登录" >  
    </form>  
  </center>
</body>
</html>