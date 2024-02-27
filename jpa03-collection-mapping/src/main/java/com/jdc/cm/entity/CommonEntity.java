package com.jdc.cm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CommonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String key;
	private int value;

}








