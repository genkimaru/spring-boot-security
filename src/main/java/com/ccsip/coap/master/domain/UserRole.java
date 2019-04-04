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
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "UserRole", description = "Please provide all required fields(*)")
@Entity
@Table(name="AD_USER_ROLE")
public class UserRole extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046812725994860938L;

	@Column(name="ROLENAME" ,nullable = false, unique = true)
	private String roleName;

	@Column(name="DESCRIPTION", nullable = false)
	private String description;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "AD_ROLE_PRIVILEGE_MAPPING", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID"))
	private Set<Privilege> privileges;

	public UserRole() {

	}

	public UserRole(long id, String roleName) {
		super.setId(id);
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "UserRole [roleName=" + roleName + ", description=" + description + ", privileges=" + privileges + "]";
	}

}
