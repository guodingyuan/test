<%@page import="com.mike.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head> 
<link rel="icon" href="../favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" /> 
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>全部用户</title>  
<script type="text/javascript">  
    function del(id){  
        $.get("/test/user/delUser?id=" + id,function(data){  
            if("success" == data.result){  
                alert("删除成功");  
                window.location.reload();  
            }else{  
                alert("删除失败");  
            }  
        });  
    }  
</script>  
</head>  
<body>  
    <%-- <c:if>没有<c:else>,可以用<c:choose>来取代结构： --%>   
	<c:choose>
	    <c:when test="${!empty userList}">
	        <h6><a href="/test/user/toAddUser">添加用户</a></h6>  
		    <table border="1">  
		        <tbody>  
		            <tr>  
		                <th>姓名</th>  
		                <th>年龄</th>  
		                <th>时间</th>
		                <th>操作</th>  
		            </tr>                         
		            <c:forEach items="${userList }" var="user">                  
	                    <tr>  
	                        <td>${user.userName}</td>  
	                        <td>${user.age}</td>                            
	                        <td>${user.createTime.replace(".0","")}</td> 
	                        <td>  
	                            <a href="/test/user/gotoLoad?id=${user.id}&name=${user.userName}">登录</a>   
	                            <a href="/test/user/getUser?id=${user.id }">编辑</a>  
	                            <a href="javascript:del('${user.id }')">删除</a>  
	                        </td>  
	                    </tr>         
		            </c:forEach>   
		        </tbody>  
		    </table>          
	    </c:when>
	    <c:otherwise>
	       <% response.sendRedirect("/test/user/getAllUser"); %>
	    </c:otherwise>
	</c:choose>  
	
	<%-- 
		<jsp:useBean id="user" class="com.mike.bean.User" scope="page"></jsp:useBean>
		<jsp:setProperty property="userName" name="user" value="郭锭源"/>
		${pageScope.user.userName} 
	--%>
</body>  
</html> 