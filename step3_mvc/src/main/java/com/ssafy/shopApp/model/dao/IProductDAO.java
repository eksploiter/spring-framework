package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

public interface IProductDAO {

	boolean insertProduct(Connection conn, ProductDTO product) throws SQLException;

	ProductDTO getProductById(Connection conn, int productId) throws SQLException;

	boolean updateProduct(Connection conn, ProductDTO product) throws SQLException;

	boolean deleteProduct(Connection conn, int productId) throws SQLException;

	List<ProductDTO> getAllProducts(Connection conn) throws SQLException;

	boolean insertGiftProduct(Connection conn, GiftProductDTO gift) throws SQLException;

}