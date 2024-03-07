package com.jdc.rm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Transient
	private boolean revamp;
	
//	@OneToMany
//	private List<Skin> skins;

	@OneToOne(mappedBy = "heroEntity")
	private HeroRole heroRole;
	
	@ManyToMany
//	@JoinTable(
//			joinColumns = @JoinColumn(name = "hero_id"),
//			inverseJoinColumns = @JoinColumn(name = "battle_id")
//		)
	private List<Battle> battles;
		
	public enum Difficulty {
		Eazy, Normal, Hard
	}

}