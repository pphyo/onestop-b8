package com.jdc.bv.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@NotBlank(message = "Please enter name")
	@Column(nullable = false)
	private String name;
	
	@Min(value = 150000, message = "Please enter salary above 150000")
	private double salary;
	
	@NotBlank(message = "Please enter phone")
	@Pattern(regexp = "(\\+?95|0?9)7(9|8|7)\\d{7}$", message = "Please enter correct phone number")
	private String phone;
	
	@NotBlank(message = "Please enter email")
	@Email(message = "Please enter correct email")
	private String email;
	
	@Past
	@Column(name = "birth_date")
	private LocalDate birthDate;

}
