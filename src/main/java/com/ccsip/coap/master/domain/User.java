package com.ccsip.coap.master.domain;

import java.io.Serializable;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "User", description = "Please provide all required fields(*)")
@Entity
@Table(name = "AD_USER")
@NamedEntityGraphs({ @NamedEntityGraph(name = "user.all", attributeNodes = {
		@NamedAttributeNode(value = "roles", subgraph = "privileges") }, subgraphs = { // subgraphs
																						// 来定义关联对象的属性
				@NamedSubgraph(name = "privileges", // 一层延伸
						attributeNodes = @NamedAttributeNode("privileges")) }) })
public class User extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046812725994860938L;

	@Column(name="NAME" ,nullable = false, unique = true)
	private String name;

	@Column(name="PASSWORD" ,nullable = false)
	private String password;

	@Column(name="EMAIL" ,nullable = false, unique = true)
	private String email;

	@Column(name="PHONE" ,nullable = true)
	private String phone;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "AD_USER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<UserRole> roles;

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", phone=" + phone + ", roles="
				+ roles + "]";
	}

}
