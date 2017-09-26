package com.mike.bean;

import java.util.List;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_customer")
public class Customer {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@Column(length = 32)
	private String name;

	@Column(length = 32)
	private String age;

	@OneToMany(mappedBy = "customer")//默认懒加载，fetch = FetchType.LAZY
	private List<RelationMap> relationMaps;

	// 表示不作为数据库中的类，不然会报错的
	@Transient
	private boolean delete = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public List<RelationMap> getRelationMaps() {
		return relationMaps;
	}

	public void setRelationMaps(List<RelationMap> relationMaps) {
		this.relationMaps = relationMaps;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
