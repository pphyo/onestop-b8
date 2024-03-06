package com.jdc.rm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Table(name = "hero") @Data
public class Hero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 40)
	private String name;
	@Column(nullable = false)
	private int skill = 4;
	private Difficulty difficulty;
	
	public enum Difficulty {
		Eazy, Normal, Hard
	}

}
