package com.curso.demojpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentId implements Serializable{
	
	private static final long serialVersionUID = -3727339055342161077L;

	@Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;
	
}
