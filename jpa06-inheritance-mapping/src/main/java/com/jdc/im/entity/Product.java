package com.jdc.im.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.jdc.im.entity.support.ProductConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends AuditEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	@Convert(converter = ProductConverter.class)
	private String name;
	
	private double price;
	
	private int stock;

}