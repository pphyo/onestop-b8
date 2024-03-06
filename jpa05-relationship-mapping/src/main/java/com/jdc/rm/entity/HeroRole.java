package com.jdc.rm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "hero_role")
@Data
public class HeroRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(name = "role_name", nullable = false, length = 40, unique = true)
	private String roleName;
	
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	@MapsId
	private Hero hero;
	
//	public void setHero(Hero hero) {
//		this.hero = hero;
//		this.id = hero.getId();
//	}

}






