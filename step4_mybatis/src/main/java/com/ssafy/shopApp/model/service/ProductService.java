package com.ssafy.shopApp.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.shopApp.model.dao.IProductDAO;
import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	private final IProductDAO productDAO;


	@Override
	public boolean insertProduct(ProductDTO product) throws Exception {
		return productDAO.insertProduct(product);
	}

	@Override
	public ProductDTO getProductById(int productId) throws Exception {
		return productDAO.getProductById(productId);
	}

	@Override
	public boolean updateProduct(ProductDTO product) throws Exception {
		return productDAO.updateProduct(product);
	}

	@Override
	public boolean deleteProduct(int productId) throws Exception {
		return productDAO.deleteProduct(productId);
	}

	@Override
	public List<ProductDTO> getAllProducts() throws Exception {
		return productDAO.getAllProducts();
	}
	
	public List<GiftProductDTO> getGiftsByProductId(int productId) {
	    return productDAO.getGiftsByProductId(productId);
	}
}
