package com.ssafy.shopApp.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.shopApp.model.dto.GiftProductDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GiftProductDAO implements IGiftProductDAO {

    private final SqlSession sqlSession;
    private static final String NS = "com.ssafy.shopApp.model.dao.IGiftProductDAO.";

    @Override
    public boolean insertGiftProduct(GiftProductDTO giftProduct) {
        return sqlSession.insert(NS + "insertGiftProduct", giftProduct) > 0;
    }

    @Override
    public GiftProductDTO getGiftProductById(int giftProductId) {
        return sqlSession.selectOne(NS + "getGiftProductById", giftProductId);
    }

    @Override
    public boolean updateGiftProduct(GiftProductDTO giftProduct) {
        return sqlSession.update(NS + "updateGiftProduct", giftProduct) > 0;
    }

    @Override
    public boolean deleteGiftProduct(int giftProductId) {
        return sqlSession.delete(NS + "deleteGiftProduct", giftProductId) > 0;
    }

    @Override
    public List<GiftProductDTO> getAllGiftProducts() {
        return sqlSession.selectList(NS + "getAllGiftProducts");
    }
}
