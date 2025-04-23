package com.ssafy.shopApp.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.shopApp.model.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAO implements IUserDAO {

	private final SqlSession sqlSession;
	private static final String NS = "com.ssafy.shopApp.model.dao.IUserDAO.";

	@Override
	public String login(Map<String, String> user) {
		return sqlSession.selectOne(NS + "login", user);
	}

	@Override
	public boolean register(UserDTO user) throws SQLException {
		return sqlSession.insert(NS + "register", user) == 1 ? true : false;
	}

	@Override
	public UserDTO getUserById(String userId) throws SQLException {
		return sqlSession.selectOne(NS + "getUserById", userId);
	}

}
