package com.jdc.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(
		name = "ACCOUNT",
		indexes = @Index(columnList = "gender, birth_date", name = "ACCOUNT_index_gender")
		)
@SecondaryTables({
	@SecondaryTable(name = "auth", uniqueConstraints = @UniqueConstraint(columnNames = "username", name = "ACCOUNT_unique_username")),
})
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(nullable = false, length = 30, table = "auth")
	private String username;
	@Column(nullable = false, unique = true, length = 50, table = "auth")
	private String email;
	
	@Column(nullable = false, 
			columnDefinition = "varchar(50) check(char_length(password) > 5)",
			table = "auth")
	private String password;
	
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE) // only for util date
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('Male', 'Female', 'Other')")
	private Gender gender;
	
	@Column(name = "avg_active_rate", precision = 6, scale = 2)
	private BigDecimal avgActiveRate;
	
	public enum Gender {
		Male, Female, Other
	}

}