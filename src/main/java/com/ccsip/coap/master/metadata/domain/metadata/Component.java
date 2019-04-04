package com.ccsip.coap.master.metadata.domain.metadata;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="MD_COMPONENT")
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="AIR_ID")
    private Long airId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "APP_ID", foreignKey = @ForeignKey(name = "FK_COMPONENT_APP"))
    private App app;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Server> servers;

    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/

    public Long getId() {
        return id;
    }

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

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }
}
