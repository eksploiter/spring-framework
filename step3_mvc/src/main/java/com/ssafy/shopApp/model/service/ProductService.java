package com.ssafy.shopApp.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ssafy.shopApp.model.dao.IProductDAO;
import com.ssafy.shopApp.model.dao.ProductDAO;
import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.util.DBUtil;

@Service
public class ProductService implements IProductService {

	private final IProductDAO productDAO;
	private final DBUtil dbUtil;

	@Autowired
	public ProductService(IProductDAO productDAO, DBUtil dbUtil) {
		this.productDAO = productDAO;
		this.dbUtil = dbUtil;
	}

	@Override
	public boolean insertProduct(ProductDTO product) throws Exception {
		Connection conn = dbUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			boolean result = productDAO.insertProduct(conn, product);
			conn.commit();
			return result;
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			dbUtil.close(conn);
		}
	}

	@Override
	public ProductDTO getProductById(int productId) throws Exception {
		try (Connection conn = dbUtil.getConnection()) {
			return productDAO.getProductById(conn, productId);
		}
	}

	@Override
	public boolean updateProduct(ProductDTO product) throws Exception {
		try (Connection conn = dbUtil.getConnection()) {
			return productDAO.updateProduct(conn, product);
		}
	}

	@Override
	public boolean deleteProduct(int productId) throws Exception {
		try (Connection conn = dbUtil.getConnection()) {
			return productDAO.deleteProduct(conn, productId);
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() throws Exception {
		try (Connection conn = dbUtil.getConnection()) {
			return productDAO.getAllProducts(conn);
		}
	}
}
