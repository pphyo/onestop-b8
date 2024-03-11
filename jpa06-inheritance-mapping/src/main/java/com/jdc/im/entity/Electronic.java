package com.jdc.im.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "electronic")
public class Electronic extends Product {

	private static final long serialVersionUID = 1L;
	
	private String model;
	
	@AttributeOverrides({
		@AttributeOverride(name = "companyName", 
				column = @Column(name = "main_company_name", nullable = false)),
		@AttributeOverride(name = "establishedDate",
				column = @Column(name = "main_established_date", columnDefinition = "DATE default((curdate()))")),
		@AttributeOverride(name = "address",
				column = @Column(name = "main_address"))
	})
	private Company mainCompany;
	private Company SubCompany;

}