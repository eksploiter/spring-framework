package com.ssafy.shopApp.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAO implements IUserDAO {

	private final SqlSession sqlSession;
	private static final String NS = "com.ssafy.shopApp.model.dao.IUserDAO.";
	
	@Override
	public String login(Map < String, String > user) {
		return sqlSession.selectOne(NS + "login", user);
	}
}
