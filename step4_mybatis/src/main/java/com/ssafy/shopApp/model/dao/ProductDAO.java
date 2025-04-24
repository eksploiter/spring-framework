package com.ssafy.shopApp.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ssafy.shopApp.config.AppConfig;
import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.GiftProductService;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDAO implements IProductDAO {

	private final SqlSession sqlSession;
	private static final String NS = "com.ssafy.shopApp.model.dao.IProductDAO.";

	@Override
	public boolean insertProduct(ProductDTO product) {
		return sqlSession.insert(NS + "insertProduct", product) > 0;
	}

	@Override
	public ProductDTO getProductById(int productId) {
		return sqlSession.selectOne(NS + "getProductById", productId); // 단일행이면 one
	}

	@Override
	public boolean updateProduct(ProductDTO product) {
		return sqlSession.update(NS + "updateProduct", product) > 0;
	}

	@Override
	public boolean deleteProduct(int productId) {
		return sqlSession.delete(NS + "deleteProduct", productId) > 0;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> dtos = sqlSession.selectList(NS + "getAllProducts");
		dtos.stream().forEach(s -> System.out.println(s.toString()));
		return dtos; // 다중행 리스트이면 list
	}

	@Override
	public List<GiftProductDTO> getGiftsByProductId(int productId) {
		return sqlSession.selectList(NS + "getGiftsByProductId", productId);
	}
}
