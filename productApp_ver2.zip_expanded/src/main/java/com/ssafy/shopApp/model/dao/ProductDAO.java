package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;


public class ProductDAO implements IProductDAO {

    @Override
	public void insertProduct(Connection conn, ProductDTO product) throws SQLException {
        String sql = "INSERT INTO products (name, category, brand, price, stock_quantity) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBrand());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
            	product.setProductId(generatedKeys.getInt(1));
            }
        }
    }
    
    @Override
	public ProductDTO getProductById(Connection conn, int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProductDTO product = new ProductDTO(rs.getInt("product_id"),rs.getString("name"),rs.getString("category"),rs.getString("brand"),rs.getInt("price"),rs.getInt("stock_quantity"));
                return product;
            }
        }
        return null;
    }
    
    @Override
	public void updateProduct(Connection conn, ProductDTO product) throws SQLException {
        String sql = "UPDATE products SET name=?, category=?, brand=?, price=?, stock_quantity=? WHERE product_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBrand());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getProductId());
            stmt.executeUpdate();
        }
    }
    
    @Override
	public void deleteProduct(Connection conn, int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }
    
    @Override
	public List<ProductDTO> getAllProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM products";
        List<ProductDTO> productList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                productList.add(product);
            }
        }
        return productList;
    }
    
    @Override
	public void insertGiftProduct(Connection conn, GiftProductDTO gift) throws SQLException {
        String sql = "INSERT INTO gift_products (name, product_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gift.getName());
            stmt.setInt(2, gift.getProductId());
            stmt.executeUpdate();
        }
        
    }
}
