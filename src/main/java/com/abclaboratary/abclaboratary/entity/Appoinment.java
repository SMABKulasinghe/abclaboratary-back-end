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
@Table(name="appoinment")
public class Appoinment implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appoinment_id", unique = true, nullable = false)
	private Long appoinmentId;
	
	@Column(name="doctor_id")
	private Long doctorId;
	
	@Column(name="report_id")
	private Long reportId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="appoinment_date")
	private Date appoinmentDate;
	
	@Column(name="status")
	private Long status;
	
	@Column(name="appoinment_no")
	private String appoinmentNo;

	public Long getAppoinmentId() {
		return appoinmentId;
	}

	public void setAppoinmentId(Long appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getAppoinmentDate() {
		return appoinmentDate;
	}

	public void setAppoinmentDate(Date appoinmentDate) {
		this.appoinmentDate = appoinmentDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getAppoinmentNo() {
		return appoinmentNo;
	}

	public void setAppoinmentNo(String appoinmentNo) {
		this.appoinmentNo = appoinmentNo;
	}

	
	
	

}
