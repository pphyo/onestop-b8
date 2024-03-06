package com.jdc.rm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "skin")
@Data
public class Skin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 40)
	private String name;
	private SkinLevel level = SkinLevel.Default;
	
	@ManyToOne
	@JoinTable(
			name = "skins_hero",
			joinColumns = @JoinColumn(name = "skin_id"),
			inverseJoinColumns = @JoinColumn(nullable = false)
		)
	private Hero hero;

	public enum SkinLevel {
		Default, Elite, Epic, Lengend, Collector, Special
	}
}
