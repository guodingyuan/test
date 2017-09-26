package com.mike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mike.bean.Customer;
import com.mike.bean.RelationMap;
import com.mike.bean.User;
import com.mike.dao.UserDao;
import com.mike.service.CustomerService;
import com.mike.service.RelationMapService;
import com.mike.service.UserService;

//注入Service
@Service
public class UserServiceImpl implements UserService {
	
	//注入Dao
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private RelationMapService relationMapService;

	public User getUser(String id) {
		 return userDao.findOne(id);
	}

	public List<User> getAllUser() {
		return userDao.findAll();
	}

	public void addUser(String jsonStr) {	
		JSONObject jsonObject=JSON.parseObject(jsonStr);
        User user=new User();
        user.setUserName(jsonObject.getString("userName"));
        user.setAge(jsonObject.getString("age"));    
        user.setPassWord(jsonObject.getString("passWord"));
        //写入User表
        User mUser=userDao.save(user);
        
    	JSONArray jsonArray=jsonObject.getJSONArray("customers");
    	List<Customer> list = JSON.parseArray(jsonArray.toString(),Customer.class);
    	System.out.println(list.toString());
    	//int a=1/0;//该行代码用于验证hibernate的事务机制
        for(Customer customer:list) {
        	//写入Customer表，并获取到生成的ID
        	Customer mCustomer=customerService.addCustomer(customer);
        	//写入RelationMap表
        	RelationMap relationMap=new RelationMap();
        	relationMap.setUser(mUser);
        	relationMap.setCustomer(mCustomer);
        	relationMapService.addRelationMap(relationMap);
        }
		
	}

	public void delUser(String id) {
		List<RelationMap> list = relationMapService.getListByUserID(id);
		for(RelationMap relationMap:list) {
			//删除Customer表中的数据
			customerService.deleteCustomer(relationMap.getCustomer().getId());
		}
		//删除RelationMap表中的数据
		relationMapService.deleteByUserID(id);
		//删除User表中的数据
		userDao.delete(id);
	}

	public void updateUser(String jsonStr) {
		JSONObject jsonObject=JSON.parseObject(jsonStr);
        String id=jsonObject.getString("id");
        String userName=jsonObject.getString("userName");
        String age=jsonObject.getString("age");    
        String passWord=jsonObject.getString("passWord");
		//更新user表
        userDao.updateUser(userName,age,passWord,id);
    	JSONArray jsonArray=jsonObject.getJSONArray("customers");
    	List<Customer> list = JSON.parseArray(jsonArray.toString(),Customer.class);
    	//更新customer表
    	customerService.updateCustomer(id,list);
	}

	@Override
	public List<User> getAllUserByPage(int page,int count) {
		return userDao.getAllUserByPage(count*(page-1),count);
	}

	@Override
	public long getTotalCount() {
		return userDao.count();
	}


}
