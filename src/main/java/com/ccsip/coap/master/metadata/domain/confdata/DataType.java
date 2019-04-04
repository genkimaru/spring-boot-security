package com.ccsip.coap.master.metadata.domain.confdata;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name="CF_DATA_TYPE")
public class DataType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "DATA_SOURCE_ID", foreignKey = @ForeignKey(name = "FK_DATA_TYPE_DATA_SOURCE"))
    private DataSource dataSource;

    @Column(name = "TARGET_TABLE", nullable = true)
    private String targetTable;

    @Column(name = "TARGET_ENTITY", nullable = true)
    private String targetEntity;
    
    @Column(name = "IS_ALERT", nullable = true , columnDefinition = "BIT DEFAULT 1")
    private boolean isAlert;
    


    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/
    public DataType() {

	}
    
    public DataType(Long id) {
		super();
		this.id = id;
	}
    
    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/
    
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

	public boolean getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(boolean isAlert) {
		this.isAlert = isAlert;
	}    
    
}
