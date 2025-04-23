package com.ssafy.shopApp.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.shopApp.model.dao.IUserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	
	private final IUserDAO userDao;
	@Override
	public String login(String userId, String password) throws Exception {
		return userDao.login(Map.of("userId", userId, "password", password));
	}
}
