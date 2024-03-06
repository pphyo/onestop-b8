package com.jdc.rm.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "battle")
@Data
public class Battle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, name = "played_date")
	private LocalDate playedDate;
	@Column(nullable = false, name = "start_time")
	private LocalTime startTime;
	@Column(nullable = false, name = "end_time")
	private LocalTime endTime;
	@Column(nullable = false)
	private BattleType type;
	
	public enum BattleType {
		Classic, Rank, Brawl
	}

}
