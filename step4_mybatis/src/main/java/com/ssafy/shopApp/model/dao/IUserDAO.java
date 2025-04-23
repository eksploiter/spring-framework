package com.ssafy.shopApp.model.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.shopApp.model.dto.UserDTO;

public interface IUserDAO {

	String login(Map < String, String > user) ;
	
	boolean register(UserDTO user) throws SQLException;
	
	UserDTO getUserById(String userId) throws SQLException;
}