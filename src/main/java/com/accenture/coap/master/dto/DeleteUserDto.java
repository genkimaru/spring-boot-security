package com.accenture.coap.master.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DeleteUserDto", description = "Please provide all required fields(*)")
public class DeleteUserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464274750614402853L;
	@ApiModelProperty(value = "User's ID, Required while update or delete an user's information.", required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
