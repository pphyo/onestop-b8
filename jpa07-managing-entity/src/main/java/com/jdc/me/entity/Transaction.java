package com.jdc.me.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "transaction")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private Ledger ledger;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "issued_date")
	private LocalDate issuedDate = LocalDate.now();
	@Column(nullable = false)
	private String issuer;
	
}