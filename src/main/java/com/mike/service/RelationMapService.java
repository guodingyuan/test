package com.mike.service;

import java.util.List;

import com.mike.bean.RelationMap;

public interface RelationMapService {
	
	public void addRelationMap(RelationMap relationMap);
	
	public List<RelationMap> getAllList();
	
    public List<RelationMap> getListByUserID(String userID);
    
    public List<RelationMap> getListByCustomerID(String customerID);
    
    public void updateRelationMap(RelationMap relationMap);
    
    public void deleteOne(String userID,String customerID);
    
    public void deleteByUserID(String userID);
}
