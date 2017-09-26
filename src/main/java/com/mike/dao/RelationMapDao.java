package com.mike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mike.bean.RelationMap;

@Repository
public interface RelationMapDao extends JpaRepository<RelationMap, Integer>{
	
	@Query(nativeQuery = true, value = "select * from t_relationmap order by user_id")
    public List<RelationMap> getAllList();
	
	@Query(nativeQuery = true, value = "select * from t_relationmap where user_id=?1")
    public List<RelationMap> getListByUserID(String userID);
    	
	@Query(nativeQuery = true, value = "select * from t_relationmap where customer_id=?1")
    public List<RelationMap> getListByCustomerID(String customerID);
    
	@Query(nativeQuery = true, value = "update t_relationmap set user_id=?1,customer_id=?2 where id=?3")
	@Modifying
    public void updateRelationMap(String userID,String customerID,Integer id);
    
	@Query(nativeQuery = true,value="delete from t_relationmap where user_id=?1 and customer_id=?2")
	@Modifying
    public void deleteOne(String userID,String customerID);
	
	@Query(nativeQuery = true,value="delete from t_relationmap where user_id=?1")
	@Modifying
    public void deleteByUserID(String userID);
}
