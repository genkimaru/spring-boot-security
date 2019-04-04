package com.ccsip.coap.master.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ccsip.coap.master.domain.User;

public class SecurityUser extends User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7451140497800281833L;
	private Collection<GrantedAuthority> authorities;

	public SecurityUser(User suser, Collection<GrantedAuthority> authorities) {
		if (suser != null) {
			this.setId(suser.getId());
			this.setName(suser.getName());
			this.setEmail(suser.getEmail());
			this.setPhone(suser.getPhone());
			this.setPassword(suser.getPassword());
			this.setAuthorities(authorities);
		}
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
