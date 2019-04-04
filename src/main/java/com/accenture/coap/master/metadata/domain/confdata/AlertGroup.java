package com.accenture.coap.master.metadata.domain.confdata;

import com.accenture.coap.master.metadata.domain.metadata.Alert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="CF_ALERT_GROUP")
public class AlertGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false , unique = true)
    private String name;

    @Column(name="REF_ID", nullable = true )
    private Long refId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "DATA_TYPE_ID", foreignKey = @ForeignKey(name = "FK_ALERT_GROUP_DATA_TYPE"))
    private DataType dataType;
    
    @ManyToMany(cascade=CascadeType.REFRESH ,fetch=FetchType.EAGER)
    @JoinTable(
            name="CF_JT_ALERT_GROUP_ALERT",
            joinColumns=@JoinColumn(name="GROUP_ID"),
            inverseJoinColumns=@JoinColumn(name="ALERT_ID")
    )
    private Set<Alert> alerts;

    public AlertGroup() {
    }
    public AlertGroup(Long id) {
    	this.id=id;
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

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

	public Set<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(Set<Alert> alerts) {
		this.alerts = alerts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertGroup other = (AlertGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
}
