<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="icon" href="../favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>添加用户</title> 
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">  
  
  
  
  function addUser(){  
	  var name=$.trim($("#userNameID").val());
	  var age=$.trim($("#userAgeID").val());
	  var password=$.trim($("#passWordID").val());
	  var re = /^\d+$/;//纯数字正则表达式
	  if(name==='') {
		  alert("姓名不能为空");
	  }else if(age===''){
		  alert("年龄不能为空");
	  }else if(!re.test(age)){
		  alert("年龄只能为数字");
	  }else if(password===''){
		  alert("密码不能为空"); 
	  }else{
		  $.ajax({
		        type: "POST",
		        url: "/test/user/addUser",
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
    }  
    
  function GetJsonData() {  
	    var json = {
	        "userName": $("#userNameID").val(),
	        "age": $("#userAgeID").val(),
	        "passWord":$("#passWordID").val(),
	        "customers":[]
	    };
	    var a={};
	    $("div input").map(function(){
	    	  var name=$(this).attr("name");
	    	  a[name]=$(this).val();
	    	  if(name==="age"){
	    	    console.log(a);
	    	    var obj = {};
	    	    obj = JSON.parse(JSON.stringify(a));//深拷贝
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
</script>  
</head>  
<body>  
    <h1>添加用户</h1>  
    <form name="userForm">  
        姓名：<input type="text" name="userName" id="userNameID"> 
        <br> 
        年龄：<input type="text" name="age" id="userAgeID">  
        <br> 
        密码：<input type="password" name="passWord" id="passWordID">  
        <br> 
        <div id="myCustomer"></div>
        <input type="button" value="添加个人客户" onclick="addCustomer()"
              style="margin-top: 10px;margin-bottom: 10px;">
        <br> 
        <input type="button" value="添加" onclick="addUser()">  
    </form>  
    
</body>  
</html>  