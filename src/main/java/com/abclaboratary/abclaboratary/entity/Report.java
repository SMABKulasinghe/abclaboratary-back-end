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
@Table(name="report")
public class Report implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_id", unique = true, nullable = false)
	private Long reportId;
	
	@Column(name="report_name")
	private String reportName;
	
	@Column(name="report_description")
	private String reportDescription;
	
	@Column(name="report_preperation")
	private String reportPreperation;
	
	@Column(name="status")
	private Long status;

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public String getReportPreperation() {
		return reportPreperation;
	}

	public void setReportPreperation(String reportPreperation) {
		this.reportPreperation = reportPreperation;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}	
	
	

}
