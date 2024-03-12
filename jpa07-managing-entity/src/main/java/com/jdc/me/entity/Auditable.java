package com.jdc.me.entity;

import java.time.LocalDateTime;

public interface Auditable {
	
	void audit(LocalDateTime now);

}
