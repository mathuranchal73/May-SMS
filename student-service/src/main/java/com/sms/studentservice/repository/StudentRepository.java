package com.sms.studentservice.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.studentservice.model.Status;
import com.sms.studentservice.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	Optional<Student> findById(Long id);
	List<Student> findByStatusId(Long id);
	List<Student> findByFirstName(String firstname);
	List<Student> findByLastName(String lastname);
}
