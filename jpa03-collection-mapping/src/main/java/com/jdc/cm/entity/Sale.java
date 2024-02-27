package com.jdc.cm.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sale")
@Data
public class Sale implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(name = "sale_date")
	private LocalDate saleDate = LocalDate.now();
	
	@Column(name = "sale_time")
	private LocalTime saleTime = LocalTime.now();

}