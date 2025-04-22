package com.ssafy.shopApp.model.service;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.shopApp.model.dao.IUserDAO;
import com.ssafy.shopApp.util.DBUtil;

@Service
public class UserService implements IUserService {
	
	private final IUserDAO userDao;
	private final DBUtil dbUtil;
	
	@Autowired
	public UserService(IUserDAO userDAO, DBUtil dbUtil) {
		this.userDao = userDAO;
		this.dbUtil = dbUtil;
	}
	
	@Override
	public String login(String userId, String password) throws Exception {
		Connection conn = dbUtil.getConnection();
		try {
			return userDao.login(conn, userId, password);
		} finally {
			dbUtil.close(conn);
		}
	}
}
