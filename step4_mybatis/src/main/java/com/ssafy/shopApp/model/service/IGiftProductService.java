package com.ssafy.shopApp.model.service;

import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;

public interface IGiftProductService {
	boolean insertGiftProduct(GiftProductDTO giftProduct);
	GiftProductDTO getGiftProductById(int giftProductId);
	boolean updateGiftProduct(GiftProductDTO giftProduct);
	boolean deleteGiftProduct(int giftProductId);
	List<GiftProductDTO> getAllGiftProducts();
}
