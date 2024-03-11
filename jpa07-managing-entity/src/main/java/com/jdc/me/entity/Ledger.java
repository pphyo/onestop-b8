package com.jdc.me.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "ledger")
public class Ledger implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	private LedgerType type;
	
	@OneToMany(mappedBy = "ledger", cascade = CascadeType.PERSIST)
	private List<Transaction> transactions;
	
	@ElementCollection
	@CollectionTable(name = "ledger_tag", 
		joinColumns = @JoinColumn(name = "ledger_id")
	)
	@Column(name = "tag", nullable = false)
	private List<String> tags;
	
	// bridge method
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
		transaction.setLedger(this);
	}
	
	public enum LedgerType {
		Credit, Debit
	}

}
