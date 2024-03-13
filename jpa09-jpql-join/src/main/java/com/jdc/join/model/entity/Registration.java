package com.jdc.join.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registration")
public class Registration {
	
	@EmbeddedId
	private Registration.RegistrationID id = new Registration.RegistrationID();
	
	@Column(nullable = false, name = "reg_date")
	private LocalDate regDate;
	
	@Column(nullable = false, name = "reg_fees")
	private double regFees;
	
	@ManyToOne
	@MapsId("studentId")
	private Student student;

	@ManyToOne
	@MapsId("courseId")
	private Course course;

	@Embeddable
	@Data
	public static class RegistrationID implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "course_id")
		private Integer courseId;
		
		@Column(name = "student_id")
		private Integer studentId;
		
	}
	
}
