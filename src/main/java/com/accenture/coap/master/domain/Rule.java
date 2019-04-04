package com.accenture.coap.master.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AD_RULE")
public class Rule extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7314784849422676250L;

	@Column(name="NAME" ,nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
