package com.ccsip.coap.master.metadata.domain.metadata;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NamedEntityGraph(name = "App.detail", attributeNodes = {
		@NamedAttributeNode(value = "components", subgraph = "components") }, subgraphs = {
				@NamedSubgraph(name = "components", attributeNodes = { @NamedAttributeNode("servers") }) })
@Entity
@Table(name="MD_APP")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="AIR_ID", nullable = false, unique = true)
    private Long airId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SERVICE_TIER", nullable = false)
    private String serviceTier;

    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Component> components;

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

    public String getServiceTier() {
        return serviceTier;
    }

    public void setServiceTier(String serviceTier) {
        this.serviceTier = serviceTier;
    }

    public Set<Component> getComponents() {
        return components;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }
}
