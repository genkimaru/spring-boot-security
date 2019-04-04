package com.ccsip.coap.master.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginDto", description = "Please provide all required fields(*)")
public class LoginDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6611623280216673171L;
	@ApiModelProperty(value = "User's nickname or real-name", required = true)
	private String userName;
	@ApiModelProperty(value = "User's Password", required = true)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
