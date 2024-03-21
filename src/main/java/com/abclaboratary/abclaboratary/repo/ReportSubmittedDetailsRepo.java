package com.abclaboratary.abclaboratary.repo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.ReportDetails;
import com.abclaboratary.abclaboratary.entity.ReportSubmittedDetails;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Component
public interface ReportSubmittedDetailsRepo extends JpaRepository<ReportSubmittedDetails,Long>{

	boolean existsByAppoinmentId(Long valueOf);

	List<ReportSubmittedDetails> findByAppoinmentId(long id);


}
