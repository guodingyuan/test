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
<title>登录成功</title>
<style type="text/css">
   th,td{
     padding-top: 5px;
     padding-bottom: 5px;
     padding-left: 15px;
     padding-right: 15px;
   }
</style>
</head>
<body>
  <center>
       <h1>恭喜你，登录成功了！</h1>
       <h4>个人资料</h4>
       <table border="1">  
        <tbody>  
            <tr>  
                <th>姓名</th>  
                <td>${mUser.userName}</td>
            </tr>  
            <tr>  
                <th>年龄</th>  
                <td>${mUser.age}</td>
            </tr> 
            <tr>  
                <th>时间</th>  
                <td>${mUser.createTime.replace(".0","")}</td>
            </tr>        
	         <c:if test="${!empty customerList }">  
	                <c:forEach items="${customerList }" var="customer">    
	                    <tr><th colspan="2" style="color: blue">个人客户</th></tr>              
	                    <tr>  
	                        <th>姓名</tj> 
	                        <td>${customer.name}</td> 
	                    </tr>
	                    <tr>  
	                        <th>年龄</th> 
	                        <td>${customer.age}</td> 
	                    </tr>            
	                </c:forEach>  
	            </c:if>
        </tbody>        
    </table>        
    <p>accessToken:"${accessToken}"</p>
     <button onclick="javascript:window.location.href ='/test/user/checkToken?accessToken=${accessToken}';" style="padding:20px;margin:20px; background-color:green;color: white;font-size: 50px">检验Token的有效性</button>   
  </center>
</body>
</html>