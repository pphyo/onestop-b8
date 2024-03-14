package com.jdc.join.model.entity.treat;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jdc.join.model.entity.constants.WholesaleType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "product")
@Inheritance
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	private double price;
	
	@ElementCollection
	@CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "product_id", nullable = false))
	@Column(name = "tag_name")
	private List<String> tags;
	
	@ElementCollection
	@CollectionTable(name = "wholesale_price", joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "wholesale_type")
	@Column(name = "price")
	private Map<WholesaleType, Double> wholesalePrice;
	
}
