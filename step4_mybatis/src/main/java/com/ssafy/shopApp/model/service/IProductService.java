package com.ssafy.shopApp.model.service;

import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

public interface IProductService {

	boolean insertProduct(ProductDTO product) throws Exception;

	ProductDTO getProductById(int productId) throws Exception;

	boolean updateProduct(ProductDTO product) throws Exception;

	boolean deleteProduct(int productId) throws Exception;

	List<ProductDTO> getAllProducts() throws Exception;

	List<GiftProductDTO> getGiftsByProductId(int productId);
}