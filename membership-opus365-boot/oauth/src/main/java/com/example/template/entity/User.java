package com.example.template.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "USR_ACCNT")
public class User implements UserDetails{

	@Id
	@Getter@Setter
	private String id;
	
	@Getter@Setter
	@Column(name = "USR_ID", nullable = false)
	private String username;

	@Getter@Setter
	@Column(name = "USR_EML", nullable = false)
	private String email;
	
	@Column(name = "USR_PWD", length=400)
	@Getter@Setter
	private String password;

	@Column(name = "APV_STS_CD", nullable = false)
	@Getter@Setter
	private String role;
	
	@Transient
	@Getter@Setter
	private Collection<? extends GrantedAuthority> authorities;

	@Column(name = "ACCNT_NON_EXPIRED" )
	@Getter@Setter
	private boolean accountNonExpired = true;

	@Column(name = "ACCNT_NON_LOCKED")
	@Getter@Setter
	private boolean accountNonLocked = true;

	@Column(name = "CRED_NON_EXPIRED")
	@Getter@Setter
	private boolean credentialsNonExpired = true;

	@Column(name = "ACTV_YN")
	@Getter@Setter
	private boolean enabled = true;


}
