package com.ccsip.coap.master.metadata.domain.rawdata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="RD_APP")
public class RdApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="AIR_ID", nullable = false, unique = true)
    private Long airId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VERSION", nullable = false)
    private Long version; //epoch date

    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/

    public Long getAirId() {
        return airId;
    }

    public void setAirId(Long airId) {
        this.airId = airId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
