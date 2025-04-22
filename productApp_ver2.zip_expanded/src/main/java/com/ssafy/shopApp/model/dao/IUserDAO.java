package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUserDAO {

	String login(Connection conn, String userId, String password) throws SQLException;

}