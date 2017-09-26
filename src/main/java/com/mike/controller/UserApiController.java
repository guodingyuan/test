package com.mike.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mike.bean.Customer;
import com.mike.bean.RelationMap;
import com.mike.bean.User;
import com.mike.service.UserService;
import com.mike.token.annotation.IgnoreSecurity;
import com.mike.util.ResponseConstant;
import com.mike.util.ResponseEntity;
import com.mike.util.StringMap;

@RestController
@RequestMapping("api/user")
public class UserApiController {
	
	private static Logger log = LoggerFactory.getLogger(UserApiController.class);    
	
	 //注入service
    @Autowired
    private UserService userService;
    
    //查所有（不分页）
    @IgnoreSecurity
    @RequestMapping(value="/getAllUser", method = RequestMethod.GET)
    public ResponseEntity getAllUser() {
    	ResponseEntity responseEntity = new ResponseEntity();
		try {
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			List<User> allUser = userService.getAllUser();		
			if(allUser!=null && !allUser.isEmpty()) {
				for(User user:allUser) {
					List<RelationMap> relationMapList = user.getRelationMaps();
					List<Map<String, Object>> customerList =new ArrayList<>();
					for(RelationMap rMap:relationMapList) {
                        Customer customer=rMap.getCustomer();						
						customerList.add(new StringMap().put("id",customer.getId())
								                        .put("name", customer.getName())
								                        .put("age", customer.getAge())
								                        .map());						
					}
					Map<String, Object> map=new StringMap().put("id", user.getId())
								               .put("userName", user.getUserName())
								               .put("age", user.getAge())
								               .put("createTime", user.getCreateTime())
								               .put("customer", customerList)
								               .map();					
					data.add(map);		
					System.out.println(map.toString());
				}
				log.debug("guo_debug");
				log.info("guo_info");
				log.warn("guo_warn");
				log.error("guo_error");
			}
			Map<String, Object> pageMap=new StringMap().put("p", 1).put("totalPage", data.size()).map();
			responseEntity.success(new StringMap().put("user", data).put("page", pageMap).map(),"获取数据成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity.failure(ResponseConstant.CODE_000, "服务器发生异常，请稍后重试");
		}      
		return responseEntity;
    }
    
    //查所有（分页）
    @IgnoreSecurity
    @RequestMapping(value="/getAllUserByPage", method = RequestMethod.GET)
    public ResponseEntity getAllUserByPage(@RequestParam(name = "page", defaultValue = "1", required = false) int page , @RequestParam(name = "count", defaultValue = "4", required = false) int count) {
    	ResponseEntity responseEntity = new ResponseEntity();
    	try {
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			List<User> allUser=userService.getAllUserByPage(page,count);
			if(allUser!=null && !allUser.isEmpty()) {
				for(User user:allUser) {
					List<RelationMap> relationMapList = user.getRelationMaps();
					List<Map<String, Object>> customerList =new ArrayList<>();
					for(RelationMap rMap:relationMapList) {
                        Customer customer=rMap.getCustomer();						
						customerList.add(new StringMap().put("id",customer.getId())
								                        .put("name", customer.getName())
								                        .put("age", customer.getAge())
								                        .map());						
					}
					Map<String, Object> map=new StringMap().put("id", user.getId())
								               .put("userName", user.getUserName())
								               .put("age", user.getAge())
								               .put("createTime", user.getCreateTime())
								               .put("customer", customerList)
								               .map();					
					data.add(map);		
					System.out.println(map.toString());
				}
			}
			Map<String, Object> pageMap=new StringMap().put("p", page)
					                                   .put("count", count)
					                                   .put("totalCount", userService.getTotalCount())
					                                   .map();
			responseEntity.success(new StringMap().put("user", data).put("page", pageMap).map(),"获取数据成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity.failure(ResponseConstant.CODE_000, "服务器发生异常，请稍后重试");
		}      
		return responseEntity;
    }
    
    
    //查单个
    @IgnoreSecurity
    @RequestMapping(value="/getUser", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(name = "id") String id) {
    	ResponseEntity responseEntity = new ResponseEntity();
		try {
			User user=userService.getUser(id);
			if(user!=null) {
				List<RelationMap> relationMapList = user.getRelationMaps();
				List<Map<String, Object>> customerList =new ArrayList<>();
				for(RelationMap rMap:relationMapList) {
                    Customer customer=rMap.getCustomer();						
					customerList.add(new StringMap().put("id",customer.getId())
							                        .put("name", customer.getName())
							                        .put("age", customer.getAge())
							                        .map());						
				}
				Map<String, Object> map=new StringMap().put("id", user.getId())
							               .put("userName", user.getUserName())
							               .put("age", user.getAge())
							               .put("createTime", user.getCreateTime())
							               .put("customer", customerList)
							               .map();		
				responseEntity.success(map,"获取数据成功");
			}else {
				responseEntity.success("查无此用户","获取数据成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity.failure(ResponseConstant.CODE_000, "服务器发生异常，请稍后重试");
		}      
		return responseEntity;
    }
}
