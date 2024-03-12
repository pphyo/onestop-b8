package com.jdc.me.listener;

import java.time.LocalDateTime;

import com.jdc.me.entity.Auditable;

import jakarta.persistence.PrePersist;

public class BeforePersist {
	
	@PrePersist
	public void doBeforePersist(Object obj) {
		if(obj instanceof Auditable aud) {
			aud.audit(LocalDateTime.now());
		}
	}

}
