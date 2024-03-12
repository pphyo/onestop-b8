package com.jdc.me.listener;

import java.time.LocalDateTime;

import com.jdc.me.entity.Account;

import jakarta.persistence.PreUpdate;

public class BeforeUpdate {
	
	@PreUpdate
	public void doBeforeUpdate(Object obj) {
		if(obj instanceof Account acc) {
			acc.setUpdatedAt(LocalDateTime.now());
		}
	}

}
