package com.ssafy.shopApp.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dao.IProductDAO;
import com.ssafy.shopApp.model.dao.ProductDAO;
import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.util.DBUtil;

//Product Service
public class ProductService implements IProductService {
	private IProductDAO productDAO = new ProductDAO();

	@Override
	public void insertProduct(ProductDTO product) throws Exception {

		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			productDAO.insertProduct(conn, product);
			List<GiftProductDTO> gifts = product.getGifts();
			if (gifts != null) {
				for (GiftProductDTO g : gifts) {
					productDAO.insertGiftProduct(conn, g);
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public ProductDTO getProductById(int productId) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			return productDAO.getProductById(conn, productId);
		} finally {
			DBUtil.close(conn);
		}

	}

	@Override
	public void updateProduct(ProductDTO product) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			productDAO.updateProduct(conn, product);
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public void deleteProduct(int productId) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			productDAO.deleteProduct(conn, productId);
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			return productDAO.getAllProducts(conn);
		} finally {
			DBUtil.close(conn);
		}
	}

}