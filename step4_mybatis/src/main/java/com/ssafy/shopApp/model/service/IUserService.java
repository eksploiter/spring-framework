package com.ssafy.shopApp.model.service;

import com.ssafy.shopApp.model.dto.UserDTO;

public interface IUserService {

	String login(String userId, String password) throws Exception;

	boolean register(UserDTO user) throws Exception;

	UserDTO getUserById(String userId) throws Exception;

}