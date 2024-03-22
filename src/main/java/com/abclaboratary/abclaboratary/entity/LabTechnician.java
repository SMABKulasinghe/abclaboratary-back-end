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
@Table(name="lab_technician")
public class LabTechnician implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lab_technician_id", unique = true, nullable = false)
	private Long labTechnicianId;
	
	@Column(name="lab_technician_name")
	private String labTechnicianName;
	
	@Column(name="lab_technician_email")
	private String labTechnicianEmail;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="status")
	private Long status;

	@Column(name="lab_technician_phone")
	private String labTechnicianPhone;
	
	@Column(name="lab_technician_register_no")
	private String labTechnicianRegisterNo;

	@Column(name="gender")
	private String gender;
	
	@Column(name="lab_technician_dob")
	private Date labTechnicianDob;

	public Long getLabTechnicianId() {
		return labTechnicianId;
	}

	public void setLabTechnicianId(Long labTechnicianId) {
		this.labTechnicianId = labTechnicianId;
	}

	public String getLabTechnicianName() {
		return labTechnicianName;
	}

	public void setLabTechnicianName(String labTechnicianName) {
		this.labTechnicianName = labTechnicianName;
	}

	public String getLabTechnicianEmail() {
		return labTechnicianEmail;
	}

	public void setLabTechnicianEmail(String labTechnicianEmail) {
		this.labTechnicianEmail = labTechnicianEmail;
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

	public String getLabTechnicianPhone() {
		return labTechnicianPhone;
	}

	public void setLabTechnicianPhone(String labTechnicianPhone) {
		this.labTechnicianPhone = labTechnicianPhone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLabTechnicianDob() {
		return labTechnicianDob;
	}

	public void setLabTechnicianDob(Date labTechnicianDob) {
		this.labTechnicianDob = labTechnicianDob;
	}

	public String getLabTechnicianRegisterNo() {
		return labTechnicianRegisterNo;
	}

	public void setLabTechnicianRegisterNo(String labTechnicianRegisterNo) {
		this.labTechnicianRegisterNo = labTechnicianRegisterNo;
	}
	
	
	
}
