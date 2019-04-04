package com.accenture.coap.master.metadata.domain.metadata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MD_SERVER")
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name="AIR_ID", nullable = false)
    private Long airId;

    @ManyToOne
    @JoinColumn(name = "COMPONENT_ID", foreignKey = @ForeignKey(name = "FK_SERVER_COMPONENT"))
    private Component component;

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

    public Long getAirId() {
        return airId;
    }

    public void setAirId(Long airId) {
        this.airId = airId;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
