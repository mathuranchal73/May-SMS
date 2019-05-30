package com.sms.studentservice.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.client.http.HttpResponse;
import com.sms.studentservice.model.Student;
import com.sms.studentservice.payload.StudentRequest;
import com.sms.studentservice.repository.StudentRepository;
//import com.sms.studentservice.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/student")
@Api(value="student", description = "Data service operations on Students", tags=("students"))
public class StudentController {
	
	

	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	
	@Autowired
    private StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="Get All Students", notes="Gets all students in the system", nickname="getStudents")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Student> findAll(@RequestParam(name="studentID", required = false)Long studentID){
    	logger.info("GET /student");
       /** if(StringUtils.isEmpty(studentID)){
            return Collections.singletonList(this.studentRepository.findByStudentID(studentID));
        }**/
        return (List<Student>) this.studentRepository.findAll();
    }
    
    @RequestMapping(value="/{statusID}",method = RequestMethod.GET)
    @ApiOperation(value="Get All Students with Status{StatusID}", notes="Gets all students with Status{StatusID} in the system", nickname="getStudentsWith StatusID")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Student> findByStatusId(@RequestParam(name="statusID", required = true)Long statusID){
    	logger.info("GET /student");
      
        return (List<Student>) this.studentRepository.findByStatusId(statusID);
    }
    
    @RequestMapping(value="/{firstname}", method = RequestMethod.GET)
    @ApiOperation(value="Get All Students with Firstname", notes="Gets all students with firstname in the system", nickname="getStudentsWithFirstName")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Student> findByFirstName(@RequestParam(name="firstname", required = true)String firstname){
    	logger.info("GET /student");
      
        return (List<Student>) this.studentRepository.findByFirstName(firstname);
    }
	
	@RequestMapping(method = RequestMethod.POST)
    //@PreAuthorize("hasRole('USER')")
	@ApiOperation(value="Create a Student", notes="Add a student in the system", nickname="postStudent")
    public void createStudent(@Valid @RequestBody Student student) {
    studentRepository.save(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{studentId}")
                .buildAndExpand(student.getId()).toUri();

    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value="Update a Student", notes="Updates the details of Student in the system based on Student ID", nickname="putStudent")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
	    public Student updateStudent(@RequestParam(name="Student_id", required = true)Long studentID,
	                                   @Valid @RequestBody StudentRequest Student_Details) {
	        return studentRepository.findById(studentID)
	                .map(student -> {
	                	student.setFirstName(Student_Details.getFirstName());
	                	student.setLastName(Student_Details.getLastName());
	                	student.setFatherName(Student_Details.getFatherName());
	                	student.setMotherName(Student_Details.getMotherName());
	                    return studentRepository.save(student);
	                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentID));
	    }
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value="Deletes a Student", notes="Deletes the Student record in the system based on Student ID", nickname="deleteStudent")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<?> deleteStudent(@RequestParam(name="Student_id", required = true)Long studentID) {
        return studentRepository.findById(studentID)
                .map(student -> {
                	studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentID));
    }
	
	/**@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value="Activate/Deactivate a Student", notes="Activate/Deactivate the Student in the system based on Student ID", nickname="activate/deactivate Student")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<?> updateStudentStatus(@RequestParam(name="Student_id", required = true)Long studentID) {
        return studentRepository.findById(studentID)
                .map(student -> {
                	if(student.getStatus().getId()==2||student.getStatus().getId()==3){student.setStatus(1)}student.setStatus(0);
                	studentRepository.save(student);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentID));
    }**/
	
	

	
}
