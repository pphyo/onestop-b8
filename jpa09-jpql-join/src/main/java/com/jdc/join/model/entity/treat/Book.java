package com.jdc.join.model.entity.treat;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Book extends Product {

	private static final long serialVersionUID = 1L;
	
	private String isbn;
	private String author;

}
