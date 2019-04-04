package com.ccsip.coap.master.metadata.domain.confdata;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedEntityGraph(name = "AlertSource.detail", attributeNodes = { @NamedAttributeNode("level"),
		@NamedAttributeNode("parent"), @NamedAttributeNode("children"),
		@NamedAttributeNode(value = "dataType", subgraph = "dataType"), @NamedAttributeNode("statisticalThreshold"),
		@NamedAttributeNode(value = "criticalAlertList", subgraph = "criticalAlertList") }, subgraphs = {
				@NamedSubgraph(name = "criticalAlertList", attributeNodes = { @NamedAttributeNode("alertGroup") }) })
@Entity
@Table(name= "CF_ALERT_SOURCE")
// @Inheritance(strategy = InheritanceType.JOINED)
public class AlertSource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "AIR_ID", nullable = false)
	private Long airId;

	@Column(name = "REF_ID", nullable = false)
	private Long refId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "LEVEL_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_LEVEL"))
	private Level level;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "DATA_TYPE_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_DATA_TYPE"))
	private DataType dataType;

	@Embedded
	private StatisticalThreshold statisticalThreshold;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CRITICAL_ALERT_LIST_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_CRITICAL_ALERT_LIST"))
	private CriticalAlertList criticalAlertList;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private AlertSource parent;

	@OneToMany(targetEntity = AlertSource.class, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<AlertSource> children;

	@JsonIgnore
	@Column(name = "STATUS", nullable = false)
	private String status = "Inactive";

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

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public AlertSource getParent() {
		return parent;
	}

	public void setParent(AlertSource parent) {
		this.parent = parent;
	}

	public Set<AlertSource> getChildren() {
		return children;
	}

	public void setChildren(Set<AlertSource> children) {
		this.children = children;
	}

	public StatisticalThreshold getStatisticalThreshold() {
		return statisticalThreshold;
	}

	public void setStatisticalThreshold(StatisticalThreshold statisticalThreshold) {
		this.statisticalThreshold = statisticalThreshold;
	}

	public CriticalAlertList getCriticalAlertList() {
		return criticalAlertList;
	}

	public void setCriticalAlertList(CriticalAlertList criticalAlertList) {
		this.criticalAlertList = criticalAlertList;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
