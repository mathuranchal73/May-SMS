package com.sms.studentservice.model;


public class Student {	
	
	 private Long id;
	  private Long personId;
	  private String firstName;
	  private String lastName;
	
	  public Student() {
		super();
	}
	
	public Student(Long id, Long personId, String firstName, String lastName) {
		super();
		this.id = id;
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 
	 
	  
	  
	
	  
	  

}
