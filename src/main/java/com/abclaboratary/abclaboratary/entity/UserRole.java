package com.abclaboratary.abclaboratary.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable{
	
	@Id
	@Column(name="user_role_id")
	private Long UserRoleId;
	
	@Column(name="user_role_name")
	private String UserRoleName;
	
	@Column(name="user_role_description")
	private String UserRoleDescription;

	public Long getUserRoleId() {
		return UserRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		UserRoleId = userRoleId;
	}

	public String getUserRoleName() {
		return UserRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		UserRoleName = userRoleName;
	}

	public String getUserRoleDescription() {
		return UserRoleDescription;
	}

	public void setUserRoleDescription(String userRoleDescription) {
		UserRoleDescription = userRoleDescription;
	}
	
	

}
