package com.abclaboratary.abclaboratary.repo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.abclaboratary.abclaboratary.entity.Patient;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Component
public interface PatientRepo extends JpaRepository<Patient,Long>{

	Optional<Patient> findByUserId(Long userId);

}
