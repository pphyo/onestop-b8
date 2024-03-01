package com.jdc.im.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "transfer_log")
//@SequenceGenerator(name = "transfer_log_sequence", allocationSize = 100, initialValue = 10)
//@TableGenerator(name = "transafer_log_table")
@Data
public class TransaferLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fromName;
	private String toName;
	private double amount;
	
}
