package com.jdc.im.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "book")
public class Book extends Product {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String title;
	private String isbn;

}
