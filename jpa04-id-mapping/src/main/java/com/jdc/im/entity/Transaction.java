package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "transaction_tbl")
//@SequenceGenerator(name = "tx_sequence")
@Data
//@TableGenerator(name = "tx_table")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	private List<User> users;
	
	@ElementCollection
	@CollectionTable(name = "archive", 
		joinColumns = @JoinColumn(name = "transaction_id"))
	private List<String> archives = new ArrayList<>();
	
	@Basic(optional = false)
	private String fromName;
	private String toName;
	private double amount;
	private LocalDateTime issuedAt;

}
