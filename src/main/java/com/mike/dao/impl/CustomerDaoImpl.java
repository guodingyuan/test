/*package com.mike.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mike.bean.Customer;
import com.mike.dao.CustomerDao;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	 @PersistenceContext
	 private EntityManager em;
	
	
	public String addCustomer(Customer customer) {
		return (String) em.unwrap(Session.class).save(customer);
	}


	public Customer getCustomer(String id) {
		String sql="select * from t_customer where id=?";
		SQLQuery query = em.unwrap(Session.class).createSQLQuery(sql);
		query.addEntity(Customer.class);
		query.setString(0, id);
		return (Customer) query.uniqueResult();
	}


	@Override
	public void updateCustomer(Customer customer) {
		String sql="update t_customer set name=?,age=? where id=?";
		SQLQuery query = em.unwrap(Session.class).createSQLQuery(sql);
		query.setString(0, customer.getName());
		query.setString(1, customer.getAge());
		query.setString(2, customer.getId());
		query.executeUpdate();
	}


	@Override
	public void deleteCustomer(String id) {
	    String sql="delete from t_customer where id=?";
	    SQLQuery query = em.unwrap(Session.class).createSQLQuery(sql);
	    query.setString(0, id);
	    query.executeUpdate();
	}
}
*/