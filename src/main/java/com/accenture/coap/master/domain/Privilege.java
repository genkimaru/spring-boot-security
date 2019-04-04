package com.accenture.coap.master.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Privilege", description = "Please provide all required fields(*)")
@Entity
@Table(name="AD_PRIVILEGE")
public class Privilege extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046812725994860938L;

	@Column(name="URI" , nullable = false, unique = true)
	private String uri;

	@Column(name="OPERATION" ,nullable = false)
	private String operation;

	public Privilege() {

	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Privilege [uri=" + uri + ", operation=" + operation + "]";
	}

}
