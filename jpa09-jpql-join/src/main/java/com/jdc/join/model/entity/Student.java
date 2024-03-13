package com.jdc.join.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.join.model.entity.constants.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	private Gender gender;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
	@MapsId("id")
	private Contact contact;

}
