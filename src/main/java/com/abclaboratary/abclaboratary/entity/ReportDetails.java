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
@Table(name="report_details")
public class ReportDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_details_id", unique = true, nullable = false)
	private Long reportDetailsId;
	
	@Column(name="report_id")
	private Long reportId;
	
	@Column(name="parameter_name")
	private String parameterName;
	
	@Column(name="parameter_scale")
	private String parameterScale;
	
	@Column(name="male_range")
	private String maleRange;
	
	@Column(name="female_range")
	private String femaleRange;
	
	@Column(name="status")
	private Long status;

	public Long getReportDetailsId() {
		return reportDetailsId;
	}

	public void setReportDetailsId(Long reportDetailsId) {
		this.reportDetailsId = reportDetailsId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterScale() {
		return parameterScale;
	}

	public void setParameterScale(String parameterScale) {
		this.parameterScale = parameterScale;
	}

	public String getMaleRange() {
		return maleRange;
	}

	public void setMaleRange(String maleRange) {
		this.maleRange = maleRange;
	}

	public String getFemaleRange() {
		return femaleRange;
	}

	public void setFemaleRange(String femaleRange) {
		this.femaleRange = femaleRange;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}	
	
	

}
