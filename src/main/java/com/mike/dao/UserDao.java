package com.mike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mike.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    
	//直接用T findOne(ID id);
	//nativeQuery = true 使用原生SQL查询
    /*@Query(nativeQuery = true, value = "select * from T_USER where id=?1")
	public User getUser(String id);*/

	//直接用Iterable<T> findAll();
   /* @Query(nativeQuery = true, value = "select * from T_USER")
    public List<User> getAllUser();*/
       
    @Query(nativeQuery = true, value = "select * from T_USER order by createTime desc limit ?1,?2")
    public List<User> getAllUserByPage(int page,int count);

    //直接用void delete(ID id);
   /* @Query(nativeQuery = true,value="delete from T_USER where id=?1")
	@Modifying
	public void delUser(String id);*/

    @Query(nativeQuery = true,value="update T_USER set userName=?1,age=?2,passWord=?3 where id=?4")
 	@Modifying
    public void updateUser(String userName,String age,String passWord,String id);
    
    //直接用long count();
   /* @Query(nativeQuery = true, value = "select count(*) from T_USER")
    public int getTotalCount();*/
}
