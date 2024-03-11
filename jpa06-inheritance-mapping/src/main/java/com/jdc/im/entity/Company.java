package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "established_date")
	private LocalDate establishedDate;
	private String address;

}