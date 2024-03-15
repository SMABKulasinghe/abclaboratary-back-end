package com.abclaboratary.abclaboratary.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name="patient")
public class Patient implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patient_id", unique = true, nullable = false)
	private Long patientId;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="patient_email")
	private String patientEmail;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="status")
	private Long status;
	
	@Column(name="patient_dob")
	private Date patientDob;

	@Column(name="patient_phone")
	private String patientPhone;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	

}
