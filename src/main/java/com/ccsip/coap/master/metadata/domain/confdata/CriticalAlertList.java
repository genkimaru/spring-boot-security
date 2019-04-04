package com.ccsip.coap.master.metadata.domain.confdata;

import com.ccsip.coap.master.metadata.domain.metadata.Alert;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by laurence.geng on 2017/7/17.
 */
@Entity
@Table(name="CF_CRITICAL_ALERT_LIST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LIST_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class CriticalAlertList {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ALERT_GROUP_ID", foreignKey = @ForeignKey(name = "FK_CRITICAL_ALERT_LIST_ALERT_GROUP"))
    private AlertGroup alertGroup;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name="CF_JT_CRITICAL_ALERT_LIST_ALERT",
            joinColumns=@JoinColumn(name="CRITICAL_ALERT_LIST_ID" , foreignKey = @ForeignKey(name = "FK_CRITICAL_ALERT_LIST_ALERT")),
            inverseJoinColumns=@JoinColumn(name="ALERT_ID", foreignKey = @ForeignKey(name = "FK_ALERT_CRITICAL_ALERT_LIST"))
    )
    private Set<Alert> additionalAlerts;

	public CriticalAlertList() {

	}

	public CriticalAlertList(Long id) {
		this.id = id;
	}

//    /*------------------------------------------     Business Methods     --------------------------------------------*/
//
//    public abstract Map<RAG,List<TSD>> filter(List<TSD> tsds);

    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}
    
    public AlertGroup getAlertGroup() {
        return alertGroup;
    }

    public void setAlertGroup(AlertGroup alertGroup) {
        this.alertGroup = alertGroup;
    }

    public Set<Alert> getAdditionalAlerts() {
        return additionalAlerts;
    }

    public void setAdditionalAlerts(Set<Alert> additionalAlerts) {
        this.additionalAlerts = additionalAlerts;
    }
}
