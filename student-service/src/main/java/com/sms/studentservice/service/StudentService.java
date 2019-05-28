package com.sms.studentservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sms.studentservice.model.Person;
import com.sms.studentservice.model.Student;
import com.sms.studentservice.repository.StudentRepository;

public class StudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	PersonService personService;
	
	@Autowired
	StudentRepository studentRepository;
	
	
	
	public Student createStudent(Student student) {
		
	   
	    Person person = personService.findPersonById(student.getPersonId());
	    if (person == null)
	    {
	    	logger.info("No Person Returned");
	      return null;
	    }
	    else
	    {
	    	return studentRepository.save(student);
	    }
	  
	  }


}
