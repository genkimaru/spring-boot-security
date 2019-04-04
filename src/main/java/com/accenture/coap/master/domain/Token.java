package com.accenture.coap.master.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="AD_TOKEN")
public class Token extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8155089861501755083L;

	@Column(name="PUBLICKEY" , nullable = false)
	private String publicKey;

	@Column(name = "PRIVATEKEY" ,nullable = false, length = 1024)
	private String privateKey;

	@Column(name="ISVERIFIED" , nullable = false)
	private int isVerified = 0;// 0 unverified, 1 Verified

	@Column(name="USERID" , nullable = true)
	private Long userId;

	@Column(name="EXPIRED" ,nullable = false)
	private Date expired = new Date(System.currentTimeMillis() + 300000);

	@Column(name="LASTUSED" , nullable = true)
	private Date lastUsed;

	@Transient
	private String accessToken;

	@Transient
	private String name;

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {

		this.expired = expired;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
