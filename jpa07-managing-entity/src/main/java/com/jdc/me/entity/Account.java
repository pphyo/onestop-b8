package com.jdc.me.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ExcludeDefaultListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
@NoArgsConstructor
@ExcludeDefaultListeners
public class Account implements Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(name = "issued_at")
	private LocalDateTime issuedAt = LocalDateTime.now();
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	private double amount;

	public Account(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	@Override
	public void audit(LocalDateTime now) {
		issuedAt = now;
		updatedAt = now;
	}
}