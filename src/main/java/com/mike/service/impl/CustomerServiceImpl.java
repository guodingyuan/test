package com.mike.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mike.bean.Customer;
import com.mike.dao.CustomerDao;
import com.mike.service.CustomerService;
import com.mike.service.RelationMapService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	 @Autowired
	private RelationMapService relationMapService;

	public Customer addCustomer(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	public void updateCustomer(String userID,List<Customer> list) {
	    for(Customer customer:list) {
	    	//如何有id，说明是更改的
	    	if(customer.getId()!=null) {
	    		if(customer.isDelete()) {//说明需要删除数据的
	    			//删除RelationMap表中对应的记录
	    			relationMapService.deleteOne(userID, customer.getId());
	    			//删除Customer表中的数据
	    			deleteCustomer(customer.getId());
	    		}else {
	    			customerDao.updateCustomer(customer.getName(),customer.getAge(),customer.getId());	    			
	    		}
	    	}else {//说明需要增加数据的	
	        	//写入RelationMap表
	        	/*RelationMap relationMap=new RelationMap();
	        	relationMap.setUser(user);
	        	relationMap.setCustomer(addCustomer(customer));//写入Customer表
	        	relationMapService.addRelationMap(relationMap);*/
			}
	    }
	}

	@Override
	public void deleteCustomer(String id) {
		customerDao.delete(id);
	}

}
