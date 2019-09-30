package com.companyname.springbootcrudrest.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "USR_ACCNT")
@EntityListeners(AuditingEntityListener.class)
public class UserAccount {

	private String user_id;
	private String user_email;
	private String user_password;
	private String approval_status_code;
	private String active_yn;
	private String delete_yn;
	private String create_user_id;
	private String create_date;
	private String update_user_id;
	private String update_date;
	private boolean account_non_expired;
	private boolean account_non_locked;
	private boolean credential_non_expired;

	@Id
	@Column(name = "USR_ID", nullable = false)
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Column(name = "USR_EML", nullable = false)
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	@Column(name = "USR_PWD", nullable = false)
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	@Column(name = "APV_STS_CD", nullable = false)
	public String getApproval_status_code() {
		return approval_status_code;
	}
	public void setApproval_status_code(String approval_status_code) {
		this.approval_status_code = approval_status_code;
	}

	@Column(name = "ACTV_YN", nullable = false)
	public String getActive_yn() {
		return active_yn;
	}
	public void setActive_yn(String active_yn) {
		this.active_yn = active_yn;
	}

	@Column(name = "DEL_YN", nullable = false)
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}

	@Column(name = "CRE_USR_ID")
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	@Column(name = "CRE_DT")
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	@Column(name = "UPD_USR_ID")
	public String getUpdate_user_id() {
		return update_user_id;
	}
	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}

	@Column(name = "UPD_DT")
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	@Column(name = "ACCNT_NON_EXPIRED")
	public boolean getAccount_non_expired() {
		return account_non_expired;
	}
	public void setAccount_non_expired(boolean account_non_expired) {
		this.account_non_expired = account_non_expired;
	}

	@Column(name = "ACCNT_NON_LOCKED")
	public boolean getAccount_non_locked() {
		return account_non_locked;
	}
	public void setAccount_non_locked(boolean account_non_locked) {
		this.account_non_locked = account_non_locked;
	}

	@Column(name = "CRED_NON_EXPIRED")
	public boolean getCredential_non_expired() {
		return credential_non_expired;
	}
	public void setCredential_non_expired(boolean credential_non_expired) {
		this.credential_non_expired = credential_non_expired;
	}

}
