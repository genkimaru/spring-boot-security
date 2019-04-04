package com.accenture.coap.master.metadata.domain.confdata;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CF_ALERT_SOURCE_TEMPLATE")
public class AlertSourceTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "LEVEL_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_TEMPLATE_LEVEL"))
	private Level level;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "DATA_TYPE_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_TEMPLATE_DATA_TYPE"))
	private DataType dataType;

	@Embedded
	private StatisticalThreshold statisticalThreshold;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CRITICAL_ALERT_LIST_ID", foreignKey = @ForeignKey(name = "FK_ALERT_SOURCE_TEMPLATE_CRITICAL_ALERT_LIST"))
	private CriticalAlertList criticalAlertList;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private AlertSourceTemplate parent;

	@OneToMany(targetEntity = AlertSourceTemplate.class, cascade = {
			CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<AlertSourceTemplate> children;

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

	public StatisticalThreshold getStatisticalThreshold() {
		return statisticalThreshold;
	}

	public void setStatisticalThreshold(StatisticalThreshold statisticalThreshold) {
		this.statisticalThreshold = statisticalThreshold;
	}

	public AlertSourceTemplate getParent() {
		return parent;
	}

	public void setParent(AlertSourceTemplate parent) {
		this.parent = parent;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Set<AlertSourceTemplate> getChildren() {
		return children;
	}

	public void setChildren(Set<AlertSourceTemplate> children) {
		this.children = children;
	}
}
