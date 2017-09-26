<%@page import="com.mike.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>全部用户</title>  
</head>  
<body>  
     <% response.sendRedirect("/test/user/getAllUser"); %>
</body>  
</html> 