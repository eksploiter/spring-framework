package com.ssafy.shopApp.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.shopApp.model.dao.IGiftProductDAO;
import com.ssafy.shopApp.model.dto.GiftProductDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GiftProductService implements IGiftProductService {

	private final IGiftProductDAO giftProductDAO;

	@Override
	public boolean insertGiftProduct(GiftProductDTO giftProduct) {
		return giftProductDAO.insertGiftProduct(giftProduct);
	}

	@Override
	public GiftProductDTO getGiftProductById(int giftProductId) {
		return giftProductDAO.getGiftProductById(giftProductId);
	}

	@Override
	public boolean updateGiftProduct(GiftProductDTO giftProduct) {
		return giftProductDAO.updateGiftProduct(giftProduct);
	}

	@Override
	public boolean deleteGiftProduct(int giftProductId) {
		return giftProductDAO.deleteGiftProduct(giftProductId);
	}

	@Override
	public List<GiftProductDTO> getAllGiftProducts() {
		return giftProductDAO.getAllGiftProducts();
	}
}
