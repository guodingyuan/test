package com.mike.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mike.bean.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {
	
	/*@Query(nativeQuery = true, value = "select * from t_customer where id=?1")
	public Customer getCustomer(String id);*/
	
	@Query(nativeQuery = true, value = "update t_customer set name=?1,age=?2 where id=?3")
	@Modifying
	public void updateCustomer(String name,String age,String id);
	
	/*@Query(nativeQuery = true,value="delete from t_customer where id=?1")
	@Modifying
	public void deleteCustomer(String id);*/
}
