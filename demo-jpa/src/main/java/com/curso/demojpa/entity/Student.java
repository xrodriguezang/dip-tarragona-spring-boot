package com.curso.demojpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Student")
@Table(name = "student",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
	}
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@SequenceGenerator(
		name = "student_sequence",
		sequenceName = "student_sequence",
		allocationSize = 1,
		initialValue = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "student_sequence"
	)
	@Column(
		name = "id",
		updatable = false
	)
	private Long id;
	
	@Column(
		name = "first_name",
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String firstName;
	
	@Column(
		name = "last_name",
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String lastName;
	
	@Column(
		name = "email",
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String email;
	
	@Column(
		name = "age",
		nullable = false
	)
	private Integer age;
	
	@OneToOne(
		mappedBy = "student",
		orphanRemoval = true
	)
	private StudentIdCard studentIdCard;

	public Student(String firstName, String lastName, String email, Integer age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	
}
