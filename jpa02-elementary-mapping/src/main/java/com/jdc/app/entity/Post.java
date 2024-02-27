package com.jdc.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String status;
	private LocalDateTime issuedAt;

}
