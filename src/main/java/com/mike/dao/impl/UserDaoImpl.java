/*package com.mike.dao.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.mike.bean.User;
import com.mike.dao.UserDao;
//注入
@Repository
public class UserDaoImpl implements UserDao {

	 @PersistenceContext
	 private EntityManager em;
    
	public User getUser(String id) {
		String sql="select * from T_USER where id=?";
		Query query = em.unwrap(Session.class).createSQLQuery(sql).addEntity(User.class);
	    query.setString(0, id);	    
		return (User) query.uniqueResult();
	}

	public List<User> getAllUser() {
		String sql = "select * from T_USER";
        Query query = em.unwrap(Session.class).createSQLQuery(sql).addEntity(User.class);
        return query.list();
	}

	public String addUser(User user) {
		return (String) em.unwrap(Session.class).save(user);
	}

	public boolean delUser(String id) {
		String sql = "delete from T_USER where id=?";
        Query query = em.unwrap(Session.class).createSQLQuery(sql);
        query.setString(0, id);
        return (query.executeUpdate() > 0);
	}

	public boolean updateUser(User user) {
		String sql = "update T_USER set userName=?,age=?,passWord=? where id=?";
        Query query = em.unwrap(Session.class).createSQLQuery(sql);
        query.setString(0, user.getUserName());
        query.setString(1, user.getAge());
        query.setString(2, user.getPassWord());
        query.setString(3, user.getId());
        return (query.executeUpdate() > 0);
	}

	@Override
	public List<User> getAllUserByPage(int page,int count) {
	    String sql="select * from T_USER order by createTime desc limit ?,?";
	    Query query = em.unwrap(Session.class).createSQLQuery(sql).addEntity(User.class);
	    query.setInteger(0, count*(page-1));
	    query.setInteger(1, count);
        return query.list();
	}

	@Override
	public int getTotalCount() {
		String sql="select count(*) from T_USER";
		BigInteger bigInteger=(BigInteger) em.unwrap(Session.class).createSQLQuery(sql).uniqueResult();
		return bigInteger.intValue() ;
	}

}
*/