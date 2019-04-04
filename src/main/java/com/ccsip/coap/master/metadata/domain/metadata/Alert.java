package com.ccsip.coap.master.metadata.domain.metadata;

import com.ccsip.coap.master.metadata.domain.confdata.DataType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MD_ALERT")
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "SEVERITY", nullable = false)
    private String severity;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DATA_TYPE_ID", foreignKey = @ForeignKey(name = "FK_ALERT_DATA_TYPE"))
    private DataType dataType;
    
    /*--------------------------------------------    Getters/Setters    ---------------------------------------------*/
    public Alert() {

	}
    
    public Alert(Long id) {
		super();
		this.id = id;
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

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
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
		Alert other = (Alert) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
