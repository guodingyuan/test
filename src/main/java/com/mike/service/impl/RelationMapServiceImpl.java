package com.mike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mike.bean.RelationMap;
import com.mike.dao.RelationMapDao;
import com.mike.service.RelationMapService;

@Service
public class RelationMapServiceImpl implements RelationMapService{
	
	//注入Dao
    @Autowired
	private RelationMapDao relationMapDao;
	
	@Override
	public void addRelationMap(RelationMap relationMap) {
		relationMapDao.save(relationMap);
	}
	
	@Override
	public List<RelationMap> getAllList() {
		return relationMapDao.getAllList();
	}


	@Override
	public List<RelationMap> getListByUserID(String userID) {
		return relationMapDao.getListByUserID(userID);
	}

	@Override
	public List<RelationMap> getListByCustomerID(String customerID) {
		return relationMapDao.getListByCustomerID(customerID);
	}

	@Override
	public void updateRelationMap(RelationMap relationMap) {
		relationMapDao.updateRelationMap(relationMap.getUser().getId(),relationMap.getCustomer().getId(),relationMap.getId());
	}

	@Override
	public void deleteOne(String userID, String customerID) {
		relationMapDao.deleteOne(userID, customerID);
	}

	@Override
	public void deleteByUserID(String userID) {
		relationMapDao.deleteByUserID(userID);
	}
}
