package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "issued_at")
	private LocalDateTime issuedAt = LocalDateTime.now();
	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();
	@Column(name = "created_by")
	private String createdBy = "Pyae Phyo";
	@Column(name = "updated_by")
	private String updatedBy = "Pyae Phyo";

}
