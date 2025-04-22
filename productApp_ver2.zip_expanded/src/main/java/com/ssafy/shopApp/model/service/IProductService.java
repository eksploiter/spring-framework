package com.ssafy.shopApp.model.service;

import java.util.List;

import com.ssafy.shopApp.model.dto.ProductDTO;

public interface IProductService {

	void insertProduct(ProductDTO product) throws Exception;

	ProductDTO getProductById(int productId) throws Exception;

	void updateProduct(ProductDTO product) throws Exception;

	void deleteProduct(int productId) throws Exception;

	List<ProductDTO> getAllProducts() throws Exception;

}