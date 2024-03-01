package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long transactionNumber;
	private LocalDateTime transactionTime;

}
