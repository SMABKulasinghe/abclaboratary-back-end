package com.abclaboratary.abclaboratary.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name="status")
public class Status implements Serializable{
	
	@Id
	@Column(name="status_id")
	private Long statusId;
	
	@Column(name="status_name")
	private String statusName;
	
	@Column(name="status_description")
	private String statusDescription;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	

}
