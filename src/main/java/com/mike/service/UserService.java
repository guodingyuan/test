package com.mike.service;

import java.util.List;

import com.mike.bean.User;

public interface UserService {
	public User getUser(String id);

    public List<User> getAllUser();

	public List<User> getAllUserByPage(int page,int count);
    
    public void addUser(String jsonStr);

    public void delUser(String id);

    public void updateUser(String jsonStr);
    
    public long getTotalCount();
}
