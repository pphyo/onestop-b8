package com.jdc.cm.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

import com.jdc.cm.entity.constant.AgentType;
import com.jdc.cm.entity.constant.ProductType;

import lombok.Data;

@Entity @Table(name = "product")
@Data
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<ProductType> types;

	@ElementCollection
	@CollectionTable(name = "product_image", 
		joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "image", nullable = false)
	private List<String> images;
	
	@ElementCollection
	@CollectionTable(name = "category", 
		joinColumns = @JoinColumn(name = "category_id"))
	@Column(name = "name", nullable = false)
	private Set<String> categories;
	
	@ElementCollection
	@CollectionTable(name = "agent_price", 
		joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "price", nullable = false)
	@MapKeyColumn(name = "agent_name", 
		columnDefinition = "enum('Agent', 'Company', 'Supermarket')")
	@MapKeyEnumerated(EnumType.STRING)
	// if key column of map is Enum type, we will use MapKeyEnumerated
	// if key column of map is util date, we will use MapKeyTemporal
	private Map<AgentType, Integer> agentPrice;
	
	@Id
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int stock;
	
	private CommonEntity common;
	
}