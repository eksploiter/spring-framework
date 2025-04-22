package com.ssafy.shopApp.model.service;

import java.sql.Connection;

import com.ssafy.shopApp.model.dao.IUserDAO;
import com.ssafy.shopApp.model.dao.UserDAO;
import com.ssafy.shopApp.util.DBUtil;

public class UserService implements IUserService {

	private IUserDAO userDao = new UserDAO();
	
	@Override
	public String login(String userId,String password) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			return userDao.login(conn, userId, password);
		} finally {
			DBUtil.close(conn);
		}
	}
}
