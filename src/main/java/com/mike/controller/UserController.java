package com.mike.controller;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mike.bean.Customer;
import com.mike.bean.RelationMap;
import com.mike.bean.User;
import com.mike.service.UserService;
import com.mike.token.annotation.IgnoreSecurity;
import com.mike.token.manager.TokenManager;
import com.mike.util.CastUtil;

//注入controller
@Controller
@RequestMapping("/user")
public class UserController {
	 //注入service
    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenManager tokenManager;
  
    @RequestMapping("/getAllUser")
    @IgnoreSecurity
    public String getAllUser(HttpServletRequest request) {
        request.setAttribute("userList", userService.getAllUser());
        return "user/allUser.jsp";
    }

    @RequestMapping("/getUser")
    @IgnoreSecurity
    public String getUser(String id, HttpServletRequest request) {
    	User user=userService.getUser(id);
        request.setAttribute("user", user);    
        List<RelationMap> relationMaps = user.getRelationMaps();
        List<Customer> customerList=new ArrayList<>();
        for(RelationMap relationMap:relationMaps)
        	customerList.add(relationMap.getCustomer());
        request.setAttribute("customerList", customerList);
        return "user/editUser.jsp";
    }

    @RequestMapping("/toAddUser")
    @IgnoreSecurity
    public String toAddUser() {
        return "user/addUser.jsp";
    }

    @RequestMapping("/addUser")
    @ResponseBody    
    @IgnoreSecurity
    public String addUser(@RequestBody String jsonStr) {
    	System.out.println(jsonStr);   	
        try {        	
            userService.addUser(jsonStr);          
            return "success";
        } catch (Exception e) {
        	System.out.println(e.toString());
            return "error";
        }       
    }

    @RequestMapping("/delUser")
    @IgnoreSecurity
    public void delUser(String id, HttpServletResponse response) {
        response.setContentType("application/json");
        String result = "{\"result\":\"error\"}";
        try {
        	userService.delUser(id);
        	result = "{\"result\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
		}      
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (Exception e) {
            e.printStackTrace();           
        }
    }

    @RequestMapping("/updateUser")
    @ResponseBody   
    @IgnoreSecurity
    public String updateUser(@RequestBody String jsonStr) {  	
    	System.out.println(jsonStr);  
    	try {        	
    		userService.updateUser(jsonStr);   
            return "success";
        } catch (Exception e) {
        	System.out.println(e.toString());
            return "error";
        }
    }
    
    @RequestMapping("/gotoLoad")
    @IgnoreSecurity
    public String gotoLoad(String id, String name, HttpServletRequest request) {
    	  request.setAttribute("id", id);
    	  request.setAttribute("name", name);
          return "load/load.jsp";
    }
    
    @RequestMapping("/load")
    @IgnoreSecurity
    public String load(User user, HttpServletRequest request) {
    	  User mUser=userService.getUser(user.getId());    	  
    	  List<RelationMap> rmlist = mUser.getRelationMaps();
    	  List<Customer> customerList = new ArrayList<>(); 
    	  for(RelationMap relationMap:rmlist)
    		  customerList.add(relationMap.getCustomer());
    	  if(user.getPassWord()!=null && user.getPassWord().equals(mUser.getPassWord())) {   		  
    		  HttpSession session = request.getSession();
    		  session.setAttribute("mUser", mUser);
    		  session.setAttribute("customerList", customerList);
    		  //创建token
    		  session.setAttribute("accessToken",  tokenManager.createToken(CastUtil.castString(mUser.getId())));	 
    		  return "load/loadSuccess.jsp";    		  
    	  }else {
			return "load/loadFail.jsp";
		  }
    }
    
    @RequestMapping("/error")
    @IgnoreSecurity
    public String error(HttpServletRequest request) {
    	return "error/error.jsp";
    }
    
    @RequestMapping("/checkToken")
    public String checkToken(String accessToken,HttpServletRequest request) {
    	System.out.println("accessToken:"+accessToken);
		return "token/CheckTokenSuccess.html";
	}
}
