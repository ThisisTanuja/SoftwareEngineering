package com.sql.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
//specifies the name of the database table to which this entity is mapped
@Table(name = "courses")
public class Course {
	
	//marking this field as the primary key of the entity and configuring how the primary key should be generated 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//fields of the Course entity
	private String semester;
	private String course;
	private String instructor;
	private String location;

}
