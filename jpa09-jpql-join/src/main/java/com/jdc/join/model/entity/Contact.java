package com.jdc.join.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "contact")
@NoArgsConstructor
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;
	@Column(length = 15, nullable = false)
	private String phone;
	@Column(nullable = false)
	private String address;

	public Contact(String email, String phone, String address) {
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

}
