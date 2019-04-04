package com.accenture.coap.master.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserInfoDto", description = "Please provide all required fields(*)")
public class UserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8109830712137023907L;
	@ApiModelProperty(value = "User's ID, Required while update or delete an user's information.")
	private Long id;

	@ApiModelProperty(value = "User's nickname", required = true)
	private String name;

	@ApiModelProperty(value = "User's password", required = true)
	private String password;

	@ApiModelProperty(value = "User's email", required = true)
	private String email;

	@ApiModelProperty(value = "User's phone", required = true)
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
