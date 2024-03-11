package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "order_tbl")
public class Order extends AuditEntity {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderPK id;
	
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	
	@ManyToOne
	@MapsId("voucherId")
	private Voucher voucher;
	
	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "order_time")
	private LocalTime orderTime;

	@Column(name = "unit_price")
	private double unitPrice;
	
	private int quantity;
	
	@Data
	@Embeddable
	public static class OrderPK implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private int bookId;
		private int voucherId;
	}

}
