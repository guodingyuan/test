<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<title>编辑用户</title> 
<link rel="icon" href="../favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>  
<script type="text/javascript">  

  function save(){ 
	  
	 $.ajax({
	        type: "POST",
	        url: "/test/user/updateUser",
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(GetJsonData()),
	        dataType: "text",//该类型的选择得配合后台的返回值的
	       	success:function(data){ 
	            if(data==="success"){
	            	 window.location.href = "/test/user/getAllUser";
	            }else{
	            	 window.location.href = "/test/user/error";
	            }       	         	
	        },
	        error : function(data) {
	        	alert("出错：" + data);
	        }
	    });

    }  
    
  function GetJsonData() {  
	    var json = {
	    	"id": "${user.id}",	
	        "userName": $("#userNameID").val(),
	        "age": $("#userAgeID").val(),
	        "passWord":$("#passWordID").val(),
	        "customers":[]
	    };
	    var a={};
	    $("div").children("input").map(function(){
	    	  var name=$(this).attr("name");
	    	  a[name]=$(this).val();
	    	  if(name==="age"){
	    	    console.log(a);
	    	    var obj = {};
	    	    obj = JSON.parse(JSON.stringify(a));//深拷贝
	    	    if($(this).parent("div:hidden").length!==0){
	    	    	obj["delete"]=true;
	    	    } 
			    json["customers"].push(obj);
	    	  }
			})
			console.log(json);
	    return json;
	}
  
  function addCustomer(){   
      $("#myCustomer").append('<h4>个人客户</h4>')
                      .append('姓名：<input type="text" name="name">')
                      .append('<br>')
	  	              .append('年龄：<input type="text" name="age">')
	  	              .append('<br>'); 
  }
  
  function deleteCustomer(id) {
	 console.log("id："+id); 
	 $("div div").map(function(){
		 console.log("当前Id:"+$(this).children(":hidden").val());
		 if($(this).children(":hidden").val()===id)
		     $(this).hide();
	 }) 
  }
</script>   
</head>  
<body>  
    <h1>编辑用户</h1>  
    <form>  
        姓名：<input type="text" name="userName" value="${user.userName}" id="userNameID">  
        <br>
        年龄：<input type="text" name="age" value="${user.age}" id="userAgeID">  
        <br>
        密码：<input type="password" name="passWord" value="${user.passWord}" id="passWordID" >  
        <br>
    <div id="myCustomer">   
     <c:if test="${!empty customerList }"> 
              
                <c:forEach items="${customerList }" var="customer">
                    <div>
	                    <p style="margin-top: 8px;margin-bottom: 8px">
	                        <b style="font-size:20px">个人客户</b> 
	                        <input type="button" value="删除" onclick="deleteCustomer('${customer.id}')" style="margin-left: 100px">  
	                    </p>          
	                    <input type="hidden" name="id" value="${customer.id}"> 
	                                                  姓名：<input type="text" name="name" value="${customer.name}"> 
	                    <br> 
	                                                  年龄：<input type="text" name="age" value="${customer.age}">    
	                    <br>               
                    </div>                                                                         
                </c:forEach>                              
            </c:if>  
         </div>             
        <input type="button" value="添加个人客户" onclick="addCustomer()"
              style="margin-top: 10px;margin-bottom: 10px">
        <br>        
        <input type="button" value="保存" onclick="save()" >  
    </form> 
</body>  
</html>  