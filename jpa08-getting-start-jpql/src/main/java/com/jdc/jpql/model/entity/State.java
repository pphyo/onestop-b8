package com.jdc.jpql.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int stateId;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String burmese;
	@Column(nullable = false)
	private Region region;
	@Column(nullable = false)
	private String capital;
	private int population;
	
	public enum Region {
		East, West, South, North, Central
	}

}
