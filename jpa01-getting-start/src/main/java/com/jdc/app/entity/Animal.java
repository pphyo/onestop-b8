package com.jdc.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Animal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String name;
	private int age;
	private String color;

}









