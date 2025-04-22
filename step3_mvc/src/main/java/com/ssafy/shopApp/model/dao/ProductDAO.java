package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

import lombok.Getter;


@Repository
public class ProductDAO implements IProductDAO {

    @Override
	public boolean insertProduct(Connection conn, ProductDTO product) throws SQLException {
        String sql = "INSERT INTO products (name, category, brand, price, stock_quantity) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBrand());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            int num = stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
            	product.setProductId(generatedKeys.getInt(1));
            }
            return num == 1 ? true : false;
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
	public boolean updateProduct(Connection conn, ProductDTO product) throws SQLException {
        String sql = "UPDATE products SET name=?, category=?, brand=?, price=?, stock_quantity=? WHERE product_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBrand());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getProductId());
            return stmt.executeUpdate() == 1 ? true : false;
        }
    }
    
    @Override
	public boolean deleteProduct(Connection conn, int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() == 1 ? true : false;
        }
    }
    
    @Override
	public List<ProductDTO> getAllProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM products";
        List<ProductDTO> productList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = ProductDTO.builder()
                .productId(rs.getInt("product_id"))
                .name(rs.getString("name"))
                .category(rs.getString("category"))
                .brand(rs.getString("brand"))
                .price(rs.getInt("price"))
                .stockQuantity(rs.getInt("stock_quantity"))
                .build();
                productList.add(product);
            }
        }
        return productList;
    }
    
    @Override
	public boolean insertGiftProduct(Connection conn, GiftProductDTO gift) throws SQLException {
        String sql = "INSERT INTO gift_products (name, product_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gift.getName());
            stmt.setInt(2, gift.getProductId());
            return stmt.executeUpdate() == 1 ? true : false;
        }
        
    }
}
