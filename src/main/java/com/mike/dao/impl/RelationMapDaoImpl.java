/*package com.mike.dao.impl;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mike.bean.RelationMap;
import com.mike.dao.RelationMapDao;

@Repository
public class RelationMapDaoImpl implements RelationMapDao {

	 @PersistenceContext
	 private EntityManager em;
	
	@Override
	public void addRelationMap(RelationMap relationMap) {
		em.unwrap(Session.class).save(relationMap);
	}

	@Override
	public List<RelationMap> getListByUserID(String userID) {
		String  sql="select * from t_relationmap where user_id=?";
		SQLQuery createSQLQuery = em.unwrap(Session.class).createSQLQuery(sql);
		createSQLQuery.addEntity(RelationMap.class);
		createSQLQuery.setString(0, userID);
		return createSQLQuery.list();
	}

	@Override
	public List<RelationMap> getListByCustomerID(String customerID) {
		String  sql="select * from t_relationmap where customer_id=?";
		SQLQuery createSQLQuery = em.unwrap(Session.class).createSQLQuery(sql);
		createSQLQuery.addEntity(RelationMap.class);
		createSQLQuery.setString(0, customerID);
		return createSQLQuery.list();
	}

	@Override
	public boolean updateRelationMap(RelationMap relationMap) {
		String sql="update t_relationmap set user_id=?,customer_id=? where id=?";
		SQLQuery createSQLQuery = em.unwrap(Session.class).createSQLQuery(sql);
		createSQLQuery.setString(0, relationMap.getUser_id());
		createSQLQuery.setString(1, relationMap.getCustomer_id());
		createSQLQuery.setInteger(2, relationMap.getId());
		return createSQLQuery.executeUpdate()>0;
	}

	@Override
	public boolean deleteRelationMap(String userID,String customerID) {
		String sql;
		if(customerID!=null)
		    sql="delete from t_relationmap where user_id=? and customer_id=?";
		else {
			sql="delete from t_relationmap where user_id=?";
		}    
		SQLQuery createSQLQuery = em.unwrap(Session.class).createSQLQuery(sql);
		createSQLQuery.setString(0, userID);
		if(customerID!=null)
		   createSQLQuery.setString(1, customerID);
		return createSQLQuery.executeUpdate()>0;
	}

}
*/