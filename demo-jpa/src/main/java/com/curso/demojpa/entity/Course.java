package com.curso.demojpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Course")
@Table(name = "course")
public class Course {
	
	 @Id
	    @SequenceGenerator(
	            name = "course_sequence",
	            sequenceName = "course_sequence",
	            allocationSize = 1
	    )
	    @GeneratedValue(
	            strategy = GenerationType.SEQUENCE,
	            generator = "course_sequence"
	    )
	    @Column(
	            name = "id",
	            updatable = false
	    )
	    private Long id;

	    @Column(
	            name = "name",
	            nullable = false,
	            columnDefinition = "TEXT"
	    )
	    private String name;

	    @Column(
	            name = "department",
	            nullable = false,
	            columnDefinition = "TEXT"
	    )
	    private String department;
	    
	    @OneToMany(
	            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
	            mappedBy = "course"
	    )
	    private List<Enrolment> enrolments = new ArrayList<>();
}
