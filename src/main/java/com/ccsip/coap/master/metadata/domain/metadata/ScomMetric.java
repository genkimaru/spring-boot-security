package com.ccsip.coap.master.metadata.domain.metadata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MD_SCOM_METRIC")
public class ScomMetric implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="`KEY`", nullable = true)
    private String key;

    @Column(name = "NAME", nullable = false)
    private String name;

    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/
    public ScomMetric() {
    	
    }
    
	public ScomMetric(Long id, String key, String name) {
		super();
		this.id = id;
		this.key = key;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}