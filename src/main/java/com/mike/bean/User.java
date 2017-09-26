package com.mike.bean;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
// 创建的数据库表名称
@Table(name = "T_USER")
public class User {

	@Id
	// 关键字生成模式
	@GeneratedValue(generator = "uuid") // 指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator") // 生成器名称，uuid生成类
	@Column(length = 32)
	private String id;

	@Column(length = 32)
	private String userName;

	@Column(length = 32)
	private String age;

	@Column(length = 32)
	private String passWord;

	@Column
	private String createTime;

	@OneToMany(mappedBy = "user")//默认懒加载，fetch = FetchType.LAZY
	private List<RelationMap> relationMaps;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<RelationMap> getRelationMaps() {
		return relationMaps;
	}

	public void setRelationMaps(List<RelationMap> relationMaps) {
		this.relationMaps = relationMaps;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + ", passWord=" + passWord + ", createTime="
				+ createTime + "]";
	}

}
