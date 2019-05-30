package com.sms.studentservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "status")
public class Status {

	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		
		@Enumerated(EnumType.STRING)
		@NaturalId
		private StatusName name;


		public Status() {
			super();
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public StatusName getName() {
			return name;
		}


		public void setName(StatusName name) {
			this.name = name;
		}


		public Status(int id, StatusName name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		
		
		
		  

}
