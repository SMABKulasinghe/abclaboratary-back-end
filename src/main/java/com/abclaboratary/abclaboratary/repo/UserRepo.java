package com.abclaboratary.abclaboratary.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.abclaboratary.abclaboratary.entity.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Component
public interface UserRepo extends JpaRepository<User,Long>{

	boolean existsByUserEmail(String email);

	Optional<User> findByUserEmail(String email);
  
}
