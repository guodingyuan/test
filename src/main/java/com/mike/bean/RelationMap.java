package com.mike.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
// 创建的数据库表名称
@Table(name = "t_relationmap")
public class RelationMap{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 32)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)//@ManyToOne默认是立即加载
	@JoinColumn(name = "user_id", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名     
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)//@ManyToOne默认是立即加载
	@JoinColumn(name = "customer_id", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名     
	private Customer customer;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "RelationMap [id=" + id + ", user=" + user + ", customer=" + customer + "]";
	}

    
}
