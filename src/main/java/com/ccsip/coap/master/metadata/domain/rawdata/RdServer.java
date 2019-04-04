package com.ccsip.coap.master.metadata.domain.rawdata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="RD_SERVER")
public class RdServer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name="AIR_ID", nullable = false)
    private Long airId;

    @Column(name = "FUNCTION", nullable = false)
    private String function;

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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
