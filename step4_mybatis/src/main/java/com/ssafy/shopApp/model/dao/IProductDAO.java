package com.ssafy.shopApp.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

public interface IProductDAO {

	boolean insertProduct(ProductDTO product) throws SQLException;

	ProductDTO getProductById(int productId) throws SQLException;

	boolean updateProduct(ProductDTO product) throws SQLException;

	boolean deleteProduct(int productId) throws SQLException;

	List<ProductDTO> getAllProducts() throws SQLException;

	List<GiftProductDTO> getGiftsByProductId(int productId);

}