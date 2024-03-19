package com.abclaboratary.abclaboratary.repo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.Report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Component
public interface ReportRepo extends JpaRepository<Report,Long>{

	@Query("SELECT re FROM Report re WHERE re.reportName LIKE %?1% OR re.reportDescription LIKE %?1% OR re.reportPreperation LIKE %?1%")
	Page<Report> getAllReportsForTable(String parameter, PageRequest of);

}
