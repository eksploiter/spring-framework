package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

public interface IProductDAO {

	void insertProduct(Connection conn, ProductDTO product) throws SQLException;

	ProductDTO getProductById(Connection conn, int productId) throws SQLException;

	void updateProduct(Connection conn, ProductDTO product) throws SQLException;

	void deleteProduct(Connection conn, int productId) throws SQLException;

	List<ProductDTO> getAllProducts(Connection conn) throws SQLException;

	void insertGiftProduct(Connection conn, GiftProductDTO gift) throws SQLException;

}