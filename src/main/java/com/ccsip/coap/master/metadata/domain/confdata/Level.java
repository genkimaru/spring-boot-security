package com.ccsip.coap.master.metadata.domain.confdata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CF_LEVEL")
public class Level implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TOP")
	private Boolean top;

	@Column(name = "TSD_TAG")
	private String tsdTag;

	@Column(name = "TARGET_TABLE", nullable = false)
	private String targetTable;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "DATA_TYPE_ID", foreignKey = @ForeignKey(name = "FK_LEVEL_DATA_TYPE"))
	private DataType dataType;

	@Column(name = "TARGET_ENTITY", nullable = false)
	private String targetEntity;
	
	@Column(name = "NEXT_ID", nullable = true)
	private Long nextId;

	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "NEXT_ID", foreignKey = @ForeignKey(name = "FK_LEVEL_LEVEL"))
//	private Level next;
	

	/*--------------------------------------------    Getters/Setters    ---------------------------------------------*/

	public Level() {

	}
	
	public Level(Long id) {
		super();
		this.id = id;
	}
	
	/*--------------------------------------------    Getters/Setters    ---------------------------------------------*/

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public String getTsdTag() {
		return tsdTag;
	}

	public void setTsdTag(String tsdTag) {
		this.tsdTag = tsdTag;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

//	public Level getNext() {
//		return next;
//	}
//
//	public void setNext(Level next) {
//		this.next = next;
//	}

	public Long getNextId() {
		return nextId;
	}

	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}
	
}
