package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

	@Override
	public String login(Connection conn, String userId, String password) throws SQLException {
		ResultSet rs = null;
		String sql = "select name from userinfo where userid=? and password=?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, userId);
			stmt.setString(2,password);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		return null;
	}
}
