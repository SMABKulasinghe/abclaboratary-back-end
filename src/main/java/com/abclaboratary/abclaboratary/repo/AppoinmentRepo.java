package com.abclaboratary.abclaboratary.repo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.abclaboratary.abclaboratary.entity.Appoinment;
import com.abclaboratary.abclaboratary.entity.Doctor;
import com.abclaboratary.abclaboratary.entity.Patient;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Component
public interface AppoinmentRepo extends JpaRepository<Appoinment,Long>{

	List<Appoinment> findAllByStatus(long l);

	List<Appoinment> findByUserId(Long userid);

}
