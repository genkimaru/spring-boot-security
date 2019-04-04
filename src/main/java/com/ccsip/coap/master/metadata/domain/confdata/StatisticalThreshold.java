package com.ccsip.coap.master.metadata.domain.confdata;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
public class StatisticalThreshold {

    @Column(name = "RED_THRESHOLD", nullable = true)
    private Integer redThreshold;

    @Column(name = "AMBER_ABOVE_THRESHOLD", nullable = true)
    private Integer amberAboveThreshold;

    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/
    public StatisticalThreshold() {
	}
    
    public StatisticalThreshold(Integer redThreshold2, Integer amberAboveThreshold2) {
		this.redThreshold=redThreshold2;
		this.amberAboveThreshold=amberAboveThreshold2;
	}

	public Integer getRedThreshold() {
        return redThreshold;
    }

    public void setRedThreshold(Integer redThreshold) {
        this.redThreshold = redThreshold;
    }

    public Integer getAmberAboveThreshold() {
        return amberAboveThreshold;
    }

    public void setAmberAboveThreshold(Integer amberAboveThreshold) {
        this.amberAboveThreshold = amberAboveThreshold;
    }
}
