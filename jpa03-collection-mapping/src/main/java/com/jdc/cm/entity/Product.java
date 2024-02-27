package com.jdc.cm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Table(name = "PRODUCT")
@Data
public class Product implements Serializable {
	
	@ElementCollection
	private List<String> images;
	
	@Embedded
	private CommonEntity common;

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int stock;
	
}