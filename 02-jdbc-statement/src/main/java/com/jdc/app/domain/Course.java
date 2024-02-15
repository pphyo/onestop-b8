package com.jdc.app.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public record Course(int id, String name, 
		int fees, LocalDate startDate, 
		int duration) {

	public static Course from(ResultSet rs) throws SQLException {
		return new Course(rs.getInt("id"), 
				rs.getString("name"), 
				rs.getInt("fees"), 
				rs.getDate("start_date").toLocalDate(), 
				rs.getInt("duration"));
	}

}
